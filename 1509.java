//https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/
/*
The largest number can be at index n-1 or n-2 or n-3 or n-4. Choose smallest number accordingly.
*/

//APPRAOCH1: T.C - for finding largest/smallest 4 numbers - sorting - O(nlogn) -> O(n*16)
class Solution {
    public int minDifference(int[] nums) {
        
        int n = nums.length, count = 0;
        if(n <= 4)return 0;
        
        Arrays.sort(nums);
        
        int min = 3, max = n-1;
        int res = nums[max]-nums[min];
        
        while(min >= 0){
            res = Math.min(res, nums[max--] - nums[min--]);
        }
        
        return res;
    }
}

//APPROACH 2 - T.C - for finding largest/smallest 4 numbers - O(n*2*3)

class Solution {
    public int minDifference(int[] nums) {
        if(nums.length <= 4)return 0;
        
        int largest[] = new int[4], smallest[] = new int[4];
        
        getSmallestLargest(nums, largest, smallest);
        
        int res = Integer.MAX_VALUE;
        
        for(int i=0;i<4;i++){
            res = Math.min(res, largest[i]-smallest[3-i]);
        }
        
        return res;
    }
    public void getSmallestLargest(int nums[], int largest[], int smallest[]){
        Arrays.fill(largest, Integer.MIN_VALUE);
        Arrays.fill(smallest, Integer.MAX_VALUE);
        int n = nums.length;
        
        for(int i=0;i<n;i++){
            if(nums[i] > largest[0]){
                largest[0] = nums[i];
                for(int j=1;j<4;j++){
                    if(largest[j] < largest[j-1]){
                        swap(largest, j);
                    }
                }
            }
            if(nums[i] < smallest[0]){
                smallest[0] = nums[i];
                for(int j=1;j<4;j++){
                    if(smallest[j] > smallest[j-1]){
                        swap(smallest, j);
                    }
                }
            }
        }  
    }
    
    public void swap(int arr[], int i){
        int temp = arr[i];
        arr[i] = arr[i-1];
        arr[i-1] = temp;
    }
}
