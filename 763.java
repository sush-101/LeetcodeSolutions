//https://leetcode.com/problems/partition-labels/

/*similar to merge intervals - but this is already sorted based on first occurance
 so, just find the last occurance, and check if current char falls within the last occurance of prev character.
 if it falls outside then make a partition
*/

class Solution {
    public List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        int map[] = new int[26], left = 0, right = 0;
        
        for(int i=0;i<s.length();i++){
            map[s.charAt(i)-'a'] = i;
        }

        for(int i=0;i<s.length();i++){
            if(i > right){
                res.add(right-left+1);
                left = i;
            }
            right = Math.max(right, map[s.charAt(i)-'a']);
        }
        
        res.add(right - left + 1);
        return res;
    }
}
//t.c  - O(n), s.c - O(1)
