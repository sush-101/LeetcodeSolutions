/*https://leetcode.com/problems/combination-sum-iv/

approach1: brute force: Using recursion keep track of how many times a number is being included.
Eg. nums = [1,2,3], target = 4
for 1,1,1,1 count[] = {4,0,0} => use formula for finding # permutations = 4!/4!
for 1,1,2 count[] = {2,1,0} => use formula for finding # permutations = 3!/2!

1.TC is (target)^nums.length
2. factorial value does not fit in long.
*/

//Approach2: Storing intermediate results.

class Solution {
    public int combinationSum4(int[] nums, int target) {
        int dp[] = new int[target+1];
        for(int i=1;i<=target;i++){
            for(int j=0;j<nums.length;j++){
                if(i - nums[j] >= 0){
                    if(nums[j] == i)dp[i] += 1;
                    else dp[i] += dp[i-nums[j]];
                }
            }
        }
        return dp[target];
    }
}
