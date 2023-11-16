class Solution {
    public int longestOnes(int[] nums, int k) {
        int l = 0, r = 0, n = nums.length, ans = 0;
        while(r < n){
            
            if(nums[r] == 1){
                r++;
            }else{
                if(k > 0){
                    r++;
                    k--;
                }else{
                    while(nums[l]==1 && l<=r){
                        l++;
                    }
                    if(l>r){
                        r=l;
                    }else{
                        l++;
                        r++;
                    }
                }
            }

            ans = Math.max(ans, r-l);
        }
        return ans;
    }
}