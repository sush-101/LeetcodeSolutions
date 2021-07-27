//https://leetcode.com/problems/next-greater-element-ii/
//graph based approach
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length, indices[] = new int[len];
        Arrays.fill(indices,-2);
        indices[len - 1] = fillLastIndex(nums);
        for(int i=len-2;i>=0;i--){
            int j = i+1;
            while(true){
                if(i==j || j==-1){
                    indices[i] = -1;
                    break;
                }
                if(nums[j] > nums[i]){
                    indices[i] = j;
                    break;
                }else {
                    if(indices[j] == -2){
                        j++;
                    }
                    else j = indices[j];
                }
            }
        }
        int res[] = new int[len];
        for(int i=0;i<len;i++){
            res[i] = indices[i] == -1? -1 : nums[indices[i]];
        }
        return res;
    }
    public int fillLastIndex(int nums[]){
        int len = nums.length, i = len-1,j = 0;
        while(j!=i){ 
            if(nums[j] > nums[i])return j;
            j++;
        }
        return -1;
    }
}
