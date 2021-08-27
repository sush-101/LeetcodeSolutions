//https://leetcode.com/problems/combination-sum/
class Solution {
    List<List<Integer>> res;
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        recurse(candidates, 0, target, new ArrayList<Integer>());
        return res;
    }
    
    private void recurse(int candidates[], int index, int target, List<Integer> cur)
    {
        if(target == 0){
            res.add(new ArrayList<>(cur));
            return;
        }
        
        if(index == candidates.length){
            return;
        }
        
        if(target >= candidates[index]){
            cur.add(candidates[index]);
            recurse(candidates, index, target-candidates[index], cur);
            cur.remove(cur.size()-1);
        }
        
        recurse(candidates, index+1, target, cur);
    }
}
