class Solution {
    public int longestStrChain(String[] words) {
        int max = 0;
        Arrays.sort(words, (o1,o2)->o2.length()-o1.length());
        int n = words.length, local[] = new int[n];
        for(int i=0,j=i;i<n;i++,j=i){ 
            while(--j>=0){
                if(isPredecessor(words[i], words[j])){
                    local[i] = Math.max(local[i],1+local[j]);
                }
                max = Math.max(max, local[i]);
            }  
        }
        return max+1;    
    }
    
    private boolean isPredecessor(String a, String b){
        if(a.length() != b.length()-1)return false;
        
        char s1[] = a.toCharArray(), s2[] = b.toCharArray();
        for(int i=0,j=0;i<a.length();i++,j++){
            if(s1[i] == s2[j])continue;
            j++;
            if(j-i > 1)return false;
            if(s1[i] != s2[j])return false;
        }
        return true;
    }
}

/*
T.C - O(n*n*l) where n is words.length, l is string's length
S.C - O(n)
*/
