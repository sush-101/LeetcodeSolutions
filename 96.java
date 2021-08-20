//https://leetcode.com/problems/unique-binary-search-trees/

/*
Nodes can always be arranged in an (ascending/descending) order.
So, answer is same for 'n' nodes having any value given that all of them are distinct.

When we have nth node:

1. we can append nth node to all the trees formed with n-1 nodes.
2. we can make nth node as root and append all the trees with n-1 nodes to it.
3. for trees having nodes t < n - 1, we first attach nth node to them, and then different ways a tree with n-t-1 nodes can be formed.
*/

class Solution {
    public int numTrees(int n) {
        //n is always > 0
        
        if(n <= 2)return n;
        
        int dp[] = new int[n+1];
        dp[0] = 1;
        
        for(int i=1;i <= n;i++){
            if(i <= 2){
                dp[i] = i;
            }else{
                int j = i;
                while(--j > 0){
                    dp[i] += dp[j]*dp[i-j-1];
                }
                dp[i] += dp[i-1];
            }
        }
        
        return dp[n];
    }
}
