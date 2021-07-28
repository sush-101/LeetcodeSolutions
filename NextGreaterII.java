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

//similar approach optimized space

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if(nums == null || nums.length == 0)
            return new int[0];
        int max = nums[0], maxIndex = 0, len = nums.length, index[] = new int[len];
        for(int i=1;i<len;i++){
            if(nums[i] > max){
                max = nums[i];
                maxIndex = i;
            }
        }
        
        int i = maxIndex, count = len;
        while(count--!=0){
            if(nums[i] == max){
                index[i] = -1;
            }else{
                int indx = i == len-1? 0:i+1;
                while(nums[indx] <= nums[i]){
                    indx = index[indx];
                }
                index[i] = indx;
            }
            i = i == 0?len-1:i-1;
        }
       for(int k=0;k<len;k++){
           index[k] = index[k] == -1? -1 : nums[index[k]];
       } 
        return index;
    }
}
