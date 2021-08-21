//Approach 1: Dfs with memoization

class Solution {
    
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, res = 1;
        int dp[][] = new int[m][n];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                res = Math.max(res, findMax(matrix, i, j, dp, -1));
            }
        }
    
        return res;
        
    }
    public int findMax(int matrix[][], int x, int y, int[][] dp, int prev){
        int m = matrix.length, n = matrix[0].length;
        if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= prev)
            return 0;
        
        if(dp[x][y] != 0)
            return dp[x][y];
        
        int local_max = 0;
        
        //four directions
        local_max = Math.max(local_max, findMax(matrix, x-1, y, dp, matrix[x][y]));
        local_max = Math.max(local_max, findMax(matrix, x+1, y, dp, matrix[x][y]));
        local_max = Math.max(local_max, findMax(matrix, x, y+1, dp, matrix[x][y]));
        local_max = Math.max(local_max, findMax(matrix, x, y-1, dp, matrix[x][y]));
        
        
        dp[x][y] = 1 + local_max;
        return 1 + local_max;
    }
}

// T.c = O(m*n*4), space = O(m*n)


// Approach 2: graph

/*
every cell is a node. edge directed from a number to another number greater than that.
we explore nodes with indegree = 0 until we run out of nodes (greedy approach, topological sort) and reduce the indegree of associated nodes
*/

class Node{
    int x,y;
    Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int indegree[][] = new int[m][n], dir[] = {0,1,0,-1,0};
        int res = 0;
        
        Queue<Node> queue = new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<4;k++){
                    int i1 = i+dir[k], j1 = j + dir[k+1];
                    if(i1 >= 0 && i1 < m && 
                       j1 >= 0 && j1 < n && matrix[i1][j1] > matrix[i][j]){
                        indegree[i1][j1]++;
                    }
                }
            }    
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(indegree[i][j] == 0)queue.add(new Node(i,j));
            }
        }
        
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size--!=0){
                Node cur = queue.poll();
                int i = cur.x, j = cur.y;
                for(int k=0;k<4;k++){
                    int i1 = i+dir[k], j1 = j + dir[k+1];
                    if(i1 >= 0 && i1 < m && 
                       j1 >= 0 && j1 < n && matrix[i1][j1] > matrix[i][j]){
                        indegree[i1][j1]--;
                        if(indegree[i1][j1] == 0)queue.add(new Node(i1,j1));
                    }
                }
            }
            res++;
        }
        return res;
    }
}

//T.c = O(m*n*4), space = O(2*m*n)
