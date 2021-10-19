//https://leetcode.com/problems/house-robber/

/*
at a house i,
1. choose this house i, and take max until houses i-2 (0 to i-2 i.e excluding house i-1)
2. dont choose this house, take max until houses i-1 (including house i-1 or excluding house i-1)
*/

class Solution {
    public int rob(int[] nums) {
        int prevIncluded = 0, prevExcluded = 0;
        for(int x:nums){
            int curIncluded = x + prevExcluded, curExcluded = Math.max(prevIncluded, prevExcluded);
            prevIncluded = curIncluded;
            prevExcluded = curExcluded;
        }
        return Math.max(prevIncluded, prevExcluded);
    }
}
