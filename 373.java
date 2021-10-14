class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> pq = new PriorityQueue<List<Integer>>(
            (o1,o2)->
            nums1[o1.get(0)]+nums2[o1.get(1)]
            -nums1[o2.get(0)]-nums2[o2.get(1)]);
        
        int len1 = nums1.length, len2 = nums2.length;
        for(int i=0;i<len1;i++){
            List<Integer> t = new ArrayList<>();
            t.add(i);t.add(0);
            pq.add(t);
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        while(res.size()!=k && !pq.isEmpty()){
            List<Integer> cur = pq.poll(), t = new ArrayList<Integer>();
            t.add(nums1[cur.get(0)]);t.add(nums2[cur.get(1)]);
            res.add(t);
            if(cur.get(1)+1 == len2)continue;
            t = new ArrayList<Integer>();
            t.add(cur.get(0));t.add(cur.get(1)+1);
            pq.add(t);
        }
        return res;
    }
}

/*
https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
T.C - k*log(nums.length) -> 10^3*log(10^5)
S.C - O(nums.length)
*/


class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length,
        left = nums1[0]+nums2[0], right = nums1[len1-1] + nums2[len2-1];
        
        while(left <= right){
            int mid = left + (right - left)/2, count = 0;
            if(findCount(nums1, nums2, mid) >= k){
                right = mid - 1;
            }else{
                left = mid+1;
            }
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int x:nums1){
            for(int y:nums2){
                if(x+y > left)break;
                else{
                    List<Integer> t = new ArrayList<>();
                    t.add(x);t.add(y);
                    res.add(t);
                }
            }
        }
        if(res.size() <= k)return res;
        
        Collections.sort(res,(o1,o2)-> o1.get(0)+o1.get(1) -o2.get(0)-o2.get(1));
        
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for(List<Integer> t:res){
            ans.add(t); 
            if(ans.size() == k)break;
        }
        return ans;
    }
    
    private int findCount(int nums1[],int nums2[], int target){
        int limit = 0, count = 0;
        
        for(int i=nums1.length-1;i>=0;i--){
            int cur = nums1[i];
            while(limit < nums2.length){
                if(cur + nums2[limit] <= target){limit++;}
                else break;
            }
            count += limit;
        }
        return count;
    }
}
/*
S.C - O(1)
T.C - nums.length*log(range) + nums.length^2*log(nums.length^2) 
    => 10^5*log(4*10^9) + 10^10*log(10^10)
*/
