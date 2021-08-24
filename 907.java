//https://leetcode.com/problems/sum-of-subarray-minimums/

/*
Find the right boundary which is less than cur element.
Until that right boundary, we know cur is smallest.
From the right boundary, smallest will be what we have already calculated from arr[right] to arr[n]

T.C - O(n), space - O(n)
*/

class Solution {
    public int sumSubarrayMins(int[] arr) {
        long M = (long)Math.pow(10,9) + 7;
        long res;
        
        int n = arr.length, res_dp[] = new int[n], dp[] = new int[n];
        
        res_dp[n-1] = arr[n-1];
        dp[n-1] = n;
        
        res = arr[n-1];
        
        for(int i=n-2;i>=0;i--){
            int j = i+1;
            while(j<n && arr[j] >= arr[i]){
                j = dp[j];
            }
            res_dp[i] = arr[i]*(j-i) + (j==n ? 0 : res_dp[j]);
            dp[i] = j;
            res = (res+res_dp[i])%M;
        }
        
        return (int)res;
    }
}
