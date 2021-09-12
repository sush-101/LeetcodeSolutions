//https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
/*T.C - O(n)
S.C - O(1)
*/
class Solution {
    public int longestSubarray(int[] nums) {
        boolean flag = true;
        int count1 = 0, count2 = 0, res = 0;
        for(int x:nums){
            if(x == 0){
                if(flag){
                    flag = false;
                    count2++;
                }else{
                    res = Math.max(res, count1+count2-1);
                    count1 = count2-1;
                    count2 = 1;
                }
            }else{
                if(!flag)count2++;
                else count1++;
            }
        }
        res = Math.max(res, count1 + count2 - 1);
        return res;
    }
}
