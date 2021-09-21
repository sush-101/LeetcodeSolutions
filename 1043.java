//https://leetcode.com/problems/partition-array-for-maximum-sum/
class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int dp[] = new int[n];
       
        for(int i=0;i<n;i++){
            int sum = 0, maxNum = 0;
            for(int j=0;j<k && i-j>=0;j++){
                if(maxNum < arr[i-j])
                    maxNum = arr[i-j];
                
                int temp = maxNum*(j+1)+((i-j-1>=0)?dp[i-j-1]:0);
                if(sum < temp)sum = temp;
            }
            dp[i] = sum;
        }
    return dp[n-1]; 
    }
}


/*
T.C - O(n*k)
S.C - O(n)
*/
