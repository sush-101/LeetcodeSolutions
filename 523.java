//https://leetcode.com/problems/continuous-subarray-sum/

/*
When there is some consecutive sum where sum%k=0, then the % gets repeated.
eg. x1 x2 x3 x4 x5 x6, let (x2+x3)%k=0, then x1%k == (x1+x2+x3)%k because x1+x2 nullifies the modulo effect.
handle: atleast two consecutive numbers case
*/
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums.length < 2)return false;
        int n = nums.length, presum = 0;
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<n;i++){
            if(i+1<n && nums[i]%k==0 && nums[i+1]%k==0)return true;
            if(nums[i]%k==0)continue;
            presum += nums[i];
            int mod = presum%k;
            if(set.contains(mod))return true;
            if(i!=0 && mod==0)return true;
            set.add(mod);
        }
        return false; 
    }
}
//t.c-O(n),s.c-O(n)

//OR
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums.length < 2)return false;
        int n = nums.length, presum = 0;
        Set<Integer> set = new HashSet<>();
        
        for(int i=0;i<n;i++){
            presum += nums[i];
            int mod = presum%k;
            if(set.contains(mod))return true;
            if(i!=0 && mod==0)return true;
            set.add(mod);
            if(i+1<n && nums[i]%k!=0 && nums[i+1]%k==0){
                i++;
            }
        }
        return false; 
    }
}
//t.c-O(n), s.c-O(n)
