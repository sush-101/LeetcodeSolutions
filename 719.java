//https://leetcode.com/problems/find-k-th-smallest-pair-distance/
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        
        int low = 0, high = nums[nums.length-1] - nums[0];
        
        while(low <= high){
            int mid = low + (high - low)/2;
            if(isPossible(mid, nums, k))
                high = mid-1;
            else low = mid+1;
        }
        return low; 
    }
    boolean isPossible(int target, int nums[], int k){
        int left = 0, noOfPairs = 0;
        for(int right = 0;right < nums.length; right++){
            while(nums[right] - nums[left] > target){
                left++;
            }
            noOfPairs += right - left;
        }
        return noOfPairs >= k;
    }
}
/*
t.c - n*log(range) + nlogn where range = MAX value in nums array - MIN value in nums array
s.c - O(1)

difference b/n any pair lies between 0 and range


if we take "some distance" and can find #pairs whose distance <= "some distance",
we can compare such #pairs with k,
    if #pairs < k -> "some distance" has to be increased
    else #pairs >= k -> "some distance" has to be decreased
    
    
we can binary search over all possible distances a pair can have i.e binary search over (0,range)
*/
