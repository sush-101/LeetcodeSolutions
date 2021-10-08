class Solution {
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, int[]> map = new HashMap<>();
        int res = 0, temp[] = new int[2];
        for(int x:nums){
            if(map.containsKey(x))continue;
            int arr[] = {x,x};
            
            //get left of left, right of right
            arr[0] = map.getOrDefault((x-1),arr)[0];
            arr[1] = map.getOrDefault((x+1),arr)[1];
            map.put(x, arr);
            
            //update right of left
            temp = map.get(arr[0]);
            temp[1] = arr[1];
            map.put(arr[0], temp);

            //update left of right
            temp = map.get(arr[1]);
            temp[0] = arr[0];
            map.put(arr[1],temp);

            res = Math.max(res, arr[1] - arr[0] + 1);
        }
        return res;
    }
}
//https://leetcode.com/problems/longest-consecutive-sequence/
//T.C & S.C - O(n)
