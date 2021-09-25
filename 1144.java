//https://leetcode.com/problems/decrease-elements-to-make-array-zigzag/
class Solution {
    public int movesToMakeZigzag(int[] nums) {
        int n = nums.length, even = 0, odd = 0;

        for(int i=0;i<n;i++){
            int left = i-1>=0?nums[i-1]:nums[i]+1;
            int right = i+1<n?nums[i+1]:nums[i]+1;
            int moves = nums[i] - Math.min(left, right) + 1;
            if(moves <= 0)continue;
            if((i&1)==0)even += moves;
            else odd += moves;
        }
        return Math.min(even, odd);
    } 
}

/*
[10,4,4,10,10,6,2,3]
time - O(n)
space - O(1)
*/
