//https://leetcode.com/problems/palindrome-partitioning/

/*
backtracking.
Approach1:  without dp for checking if a string is a palindrome.
Approach2: checking if a string is a palindrome using dp.
*/

class Solution {
    
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        int n = s.length();
        boolean dp[][] = new boolean[n][n];
        findAll(s, res, 0, new ArrayList<String>(),dp);
        return res;
    }
    
    private void findAll(String s, List<List<String>> res, int index, ArrayList<String> cur, boolean dp[][]){
        
        if(index == s.length()){
            res.add(new ArrayList<>(cur));
            return;
        }
        
        for(int i=index+1; i<=s.length(); i++){
            if(s.charAt(i-1) != s.charAt(index) || 
               (index+1 <= i-2 && !dp[index+1][i-2]))continue;
            if(index+1 > i-2 || dp[index+1][i-2]) dp[index][i-1] = true; //storing intermediate result
            else continue;
            cur.add(s.substring(index,i));
            findAll(s,res,i,cur,dp);
            cur.remove(cur.size()-1);  //backtrack
        }
    }
}
