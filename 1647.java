class Solution {
    public int minDeletions(String s) {
        int[] map = new int[26];
        boolean flag[] = new boolean[100001];
        int count = 0;
        for(char x: s.toCharArray()){
            map[x-'a']++;
        }
        for(int i=0;i<26;i++){
            int x = map[i];
            if(x == 0)continue;
            
            if(!flag[x]){
                flag[x] = true;
            }else{
                int cur = x;
                while(cur!=0 && flag[cur]){
                    cur--;
                }
                count += x-cur;
                flag[cur] = true;
            }
        }
        return count;
    }
}
//https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
