class Solution {
public:
    int longestOnes(vector<int>& nums, int k) {
        int l = 0, r = 0, max1 = 0, n = nums.size();
        while(r < n){
            if(nums[r] == 1)r++;
            else if(k > 0){
                k--;
                r++;
            }
            else{
                while(l<=r and nums[l]!=0){l++;}
                if(l>r){
                    r=l;
                }else {
                    l++;
                    r++;
                }
            }
            max1 = max(max1,r-l);
        }
        return max1;
        
    }
};