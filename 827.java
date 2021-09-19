class Solution {
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int islandIndx = 2, res = 0, map[] = new int[n*n + 2];
        int dx[] = {-1,1,0,0}, dy[] = {0,0,-1,1};
        map[0] = 0;
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 0)continue;
                if(grid[i][j] == 1){
                    int temp = getCount(grid, i, j, islandIndx);
                    map[islandIndx] = temp;
                    islandIndx++;
                    res = Math.max(res, temp);
                }
            }
        }
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 0){
                    int temp = 0;
                    HashSet<Integer> set = new HashSet();
                    for(int k=0;k<4;k++){
                        if(check(i,j,dx[k],dy[k],n)){
                            int indx = grid[i+dx[k]][j+dy[k]];
                            if(set.contains(indx))continue;
                            temp += map[indx];
                            set.add(indx);
                        }
                    }
                    res = Math.max(res, temp+1);
                }
            }
        }
        return res;
    }
    
    private boolean check(int x, int y, int a, int b,int n){
        if(x+a<n && x+a>=0 && y+b<n && y+b>=0)return true;
        return false;
    }
    private int getCount(int grid[][], int x, int y, int islandIndx){
        int n = grid.length;
        if(x < 0 || x >= n || y < 0 || y >=n || grid[x][y] != 1)return 0;
        
        grid[x][y] = islandIndx;
        return 1 + getCount(grid, x+1, y, islandIndx) + 
            getCount(grid, x, y+1, islandIndx) + 
            getCount(grid, x-1, y, islandIndx) + 
            getCount(grid, x, y-1, islandIndx);
    }
}
/*
https://leetcode.com/problems/making-a-large-island/

Approach: 
Find disjoint sets. Maintain a map which stores set index and count of 1s in that set -> O(n^2)
For every 0, change only this 0 as 1 and add disjoint sets count in 4 directions -> O(n^2)
T.C - O(n^2)
S.C - O(n^2)

[[1,0],[0,1]]
[[1,1],[1,0]]
[[1,1],[1,1]]
*/
