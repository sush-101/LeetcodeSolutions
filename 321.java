//https://leetcode.com/problems/create-maximum-number/

class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int res[] = new int[k], len1 = nums1.length, len2 = nums2.length;
        Arrays.fill(res,-1);
        for(int i=0;i<=k;i++){
            if(i<=len1 && k-i<=len2){
                int arr1[] = getMax(nums1,i), arr2[] = getMax(nums2,k-i);
                res = compare(res, merge(arr1, arr2));
            }
        }
        return res;
    }
    public int[] getMax(int nums[], int k){
        int len = nums.length;
        if(k == len)return nums;
        int res[] = new int[k], j = 0;
        if(k == 0)return res;
        res[0] = nums[0];
        for(int i=1;i<len;i++){
            while(j>=0 && nums[i] > res[j] && j+len-i >= k){
                j--;
            }if(j+1<k)res[++j] = nums[i];
        }
        return res;
    }
    public int[] merge(int nums1[], int nums2[]){
        int i = 0, j = 0, len1 = nums1.length, len2 = nums2.length;
        int res[] = new int[len1+len2], k = 0;
        while(i < len1 && j < len2){
            boolean check = false, inc = true;
            if(nums1[i] > nums2[j]){
                res[k++] = nums1[i++];
            }else if(nums1[i] < nums2[j]){
                res[k++] = nums2[j++];
            }else{
                int temp1 = i, temp2 = j, same = 0, prev = nums1[temp1];
                while(temp1<len1 && 
                      temp2<len2 && 
                      nums1[temp1] == nums2[temp2])
                { 
                    if(nums1[temp1] < prev){
                        if(!check)break;
                        inc = false;
                    }else{
                        if(prev < nums1[temp1])check=true;
                        if(prev <= nums1[temp1] && inc)same++;
                    }
                    prev = nums1[temp1];
                    temp1++;temp2++;  
                }
                
                int temp3 = i;
                if(temp1==len1 && temp2==len2)break;
                
                if(temp1==len1){
                    j += same;
                }else if(temp2==len2)i+=same;
                else if(nums1[temp1] < nums2[temp2])j+=same;
                else i += same;
                
                while(same--!=0){
                    res[k++] = nums1[temp3++];
                }
            }
        }
        while(i < len1){
           res[k++] = nums1[i++]; 
        }
        while(j < len2){
            res[k++] = nums2[j++];
        }
        return res;
    }
    public int[] compare(int nums1[], int nums2[]){
        int len = nums1.length, i = 0;
        while(i<len && nums1[i] == nums2[i]){i++;}
        if(i == len || nums1[i] > nums2[i])return nums1;
        return nums2;
    }
}

/*
[9,9,9,9,7,9]
[9,9,9,9,7,6]
12

[2,1,0,1]
[1,0,1,0,5,6,0,5]
12

[5,0,2,1,0,1,0,3]
[1,0,1,0,5,6,0,5]
16

[1,0,2,0]
[1,1,0,2,0,1]
10
*/
