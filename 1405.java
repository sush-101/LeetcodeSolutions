class Solution {
    public String longestDiverseString(int a, int b, int c) {
        int count[] = {a,b,c};
    
        StringBuilder sb = new StringBuilder();
        int prev = -1, prevCount = 0;
        
        //a=10,b=7,c=6
        //prevc = 2, prev = 'a'
        for(int i=0;i<a+b+c;i++){
            
            int cur = getMax(count,-1);
            
            if(cur == prev && prevCount == 2){
                cur = getMax(count, cur);
                if(cur == -1)return sb.toString();
            }
            count[cur]-=1;
            if(prev == cur)
                prevCount += 1;
            else prevCount = 1;
            
            sb.append((char)('a'+cur));  
            
            prev = cur;
        }
        
        return sb.toString();
    }
    private int getMax(int count1[], int exclude){
        int count[] = {count1[0], count1[1], count1[2]};
        if(exclude!=-1)count[exclude] = 0;
        int ans = -1;
        if(count[0] >= count[1]){
            if(count[0] >= count[2])
                ans = 0;
            else ans = 2;
        }
        else if(count1[1] >= count[2])
            ans = 1;
        else ans = 2;
        return count[ans] == 0? -1:ans;
    }
}

/*
T.C - O(n)
S.C - O(1)
*/
