class Solution {
    public int minOperations(int n) {
        int i = 0, j = n-1, res = 0;
        while(i<j){
            res += j-i;
            j--;i++;
        }
        return res;
    }
}

//https://leetcode.com/problems/minimum-operations-to-make-array-equal/
