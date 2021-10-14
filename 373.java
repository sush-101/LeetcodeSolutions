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
