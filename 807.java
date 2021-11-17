//https://leetcode.com/problems/max-increase-to-keep-city-skyline/

/*
To increase the height of a building, we have to consider 4 directions.
4 directions can be consolidated into two axes -> row X axis, column Y axis

1. On the same row, the tallest building of height H1 should not be changed(because east/west views shouldn't be effected).
So remaining buildings on the same row CAN be raised to H1

2. On the same col, tallest building height H2, should not be changed (because north/south views shouldn't be effected)
So remaining buildings on the same row CAN be raised to H2

We take MIN(H1,H2) for a building to make sure views remain same.
T.c - O(m*n), space - O(m+n)

*/

class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int r = grid.length, c = grid[0].length,
        row[] = new int[r], col[] = new int[c];
        
        int res = 0;
        
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(grid[i][j] > grid[i][row[i]]){
                    row[i] = j;
                }
                if(grid[i][j] > grid[col[j]][j]){
                    col[j] = i;
                }
            }
        }
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                res += Math.min(grid[i][row[i]], grid[col[j]][j]) - grid[i][j];
            }
        }
        return res;
    }
}

//readability

class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int r = grid.length, c = grid[0].length,
        row[] = new int[r], col[] = new int[c];
        
        int res = 0;
        
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(grid[i][j] > row[i]){
                    row[i] = grid[i][j];
                }
                if(grid[i][j] > col[j]){
                    col[j] = grid[i][j];
                }
            }
        }
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                res += Math.min(row[i], col[j]) - grid[i][j];
            }
        }
        return res;
    }
}
