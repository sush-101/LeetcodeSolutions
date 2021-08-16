//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

/*
In a rotated array, nums[left] > nums[right]. If sorted, answer is nums[left].
When rotated, if nums[left] > nums[mid], then we search in left.
else we search right half.
*/

class Solution {
    public int findMin(int[] nums) {
        
        int left = 0,right = nums.length-1;
        
        while(left < right){
            //sorted
            if(nums[left] < nums[right])return nums[left];
            
            int mid = (left+right)/2;
          
            if(mid > 0 && nums[mid] < nums[mid-1])
                return nums[mid];
            
            if(nums[mid] < nums[left])right = mid-1;
            else left=mid+1;
        }
        
        return nums[left];
    }
}

/*
[2,1]
*/
