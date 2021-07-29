//https://leetcode.com/problems/set-matrix-zeroes/
class Solution {
    // use first row to store if any element in a column is having zero ,
    //and first col to store if any number in a row is zero.
    public void setZeroes(int[][] matrix) {
      // col stores 0 if any element in first column is zero , because matrix[0][0] cant store first row and first col info.
      // matrix[0][0] here stores 0 if any element in first row is zero
        int r = matrix.length, c = matrix[0].length, col = 1;
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(matrix[i][j] == 0){
                    if(j == 0){col = 0;}
                    else{
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
        }
        
        // should not mark first row and first column with zeroes because we will loose info.
        for(int i=1;i<r;i++){
            for(int j=1;j<c;j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }
        
        
        if(matrix[0][0] == 0){
            for(int j=0;j<c;j++)matrix[0][j] = 0;
        }
        if(col == 0){
            for(int i=0;i<r;i++)matrix[i][0] = 0;
        }
        
    }
}

// T.C - 2*(n^2) ~ O(n^2), Space - O(1)
