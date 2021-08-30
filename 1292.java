//https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/
//Approach1: brute force - calculate histograms sums. T.C - (m*n*n*n)

//Approach 2:
/*
dp[i][j] = sum of matrix with corners 0,0 and i,j
from each index, calculate matrix sum from dp
T.C - O(mn+Min(m,n)), space - O(mn)
*/

class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m=mat.length, n = mat[0].length, dp[][] = new int[m+1][n+1];
        
        
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1] -dp[i-1][j-1] + mat[i-1][j-1];
            }
        }
        
        
        int side = 0;
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){      
                while(i+side <= m && j+side <= n && calSquareSum(dp,i+side,j+side,i-1,j-1)<=threshold){
                    side++;
                }
            }
        }
        return side;
    }
    
    private int calSquareSum(int dp[][], int i2, int j2, int i1, int j1){
        return dp[i2][j2] - dp[i2][j1] - dp[i1][j2] + dp[i1][j1];
    }
}
