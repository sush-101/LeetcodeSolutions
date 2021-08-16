//https://leetcode.com/problems/maximum-product-subarray/

/* approach 1: Using Dp. Answer can end at any index. The subarray can start at an index or we can include element at the index to the answer.
*/

class Solution {
    public int maxProduct(int[] nums) {
        int min = nums[0], max = nums[0], res = nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i] < 0){
                int temp = min; min = max; max = temp;
            }
            min = Math.min(nums[i], min*nums[i]);
            max = Math.max(nums[i], max*nums[i]);
            res = Math.max(max,res);
        }
        return res;
    }
}

/* approach 2: 
three cases:
1. contains zeroes -> divide the array
  2. even # of -ve numbers (with no zeroes)
  3. odd # of -ve numbers (with no zeroes)

in case of even, left and right traversal gives the same result.
In case of odd, either of the two extremes is ignored in one of the traversals.
For case1, whenever we encounter 0, we reset the temp to 1.
*/

class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length, res = nums[0], cur = nums[0];
        for(int i=1;i<n;i++){
            res = Math.max(cur,res);
            if(cur == 0)cur=1;
            cur *= nums[i];   
        }
        cur = nums[n-1];
        for(int i=n-2;i>=0;i--){
            res = Math.max(cur,res);
            if(cur == 0)cur = 1;
            cur *= nums[i];
        }
        return Math.max(res,cur);
    }
}
