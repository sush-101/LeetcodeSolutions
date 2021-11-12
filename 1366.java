//https://leetcode.com/problems/rank-teams-by-votes/
/*
general sorting
t.c-n^2log(n), s.c - O(n) where n is #teams
*/

class Solution {
    public String rankTeams(String[] votes) {
        int n = votes[0].length(), freq[][] = new int[26][n];
        ArrayList<Integer> arr = new ArrayList<>();

        for(int i=0;i<26;i++){
            arr.add(i);
        }
        for(String vote: votes){
            int i=0;
            for(char x: vote.toCharArray()){
                freq[x-'A'][i++]++;
            } 
        }
        Collections.sort(arr, (o1,o2)->{
            for(int i=0;i<n;i++){
                if(freq[o2][i]!=freq[o1][i])
                return freq[o2][i]-freq[o1][i];
            }
            return o1-o2;
        }
        );
        StringBuilder res = new StringBuilder("");
        for(int i=0;i<n;i++){
            res.append((char)(arr.get(i)+'A'));
        }
        return res.toString();
    }
}
