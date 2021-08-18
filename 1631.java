//https://leetcode.com/problems/path-with-minimum-effort/

//Greedy with PriorityQueue - Dijkstra's algorithm

class Node{
    int i, j, min;
    Node(int i, int j, int min){
        this.i = i;
        this.j = j;
        this.min = min;
    }
    public String toString(){
        return this.i + " " + this.j + " " + this.min;
    }
}

class Solution {
    public int minimumEffortPath(int[][] heights) {
        int r = heights.length, c = heights[0].length;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->o1.min - o2.min);
        
        int dis[][] = new int[r][c];
        for(int[] row:dis)Arrays.fill(row,Integer.MAX_VALUE);
        
        Node start = new Node(0,0,0);
        pq.add(start);
        dis[0][0] = 0;
        int dir[][] = {{0,1},{0,-1},{-1,0},{1,0}};
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int x = cur.i, y=cur.j;
            if(x == r-1 && y == c-1)return cur.min;
            //if(heights[x][y] == 0)continue;
            
            for(int i=0;i<4;i++){
                int next_i = x+dir[i][0], next_j = y+dir[i][1];
                if(next_i < r && next_i >= 0 && next_j >= 0 && next_j < c){
                    int temp = Math.max(
                                Math.abs(heights[x][y]-heights[next_i][next_j]),
                                cur.min
                            );
                    if(temp >= dis[next_i][next_j])continue;
                    dis[next_i][next_j] = temp;
                    pq.add(new Node(next_i, next_j, temp));
                }
            }
            //heights[x][y] = 0;  
        }
        return 0;
    }
}

/*
T.C >= O(ElogV)
space - O(V)
*/
