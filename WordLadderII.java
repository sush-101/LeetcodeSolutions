//https://leetcode.com/problems/word-ladder-ii/

class Solution {
    int visited[] = null;
    Map<String, Integer> map = new HashMap<>();
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        int len = wordList.size();
        visited = new int[len+1];
        for(int i=0;i<len;i++){
            map.put(wordList.get(i),i+1);     
        }map.put(beginWord,0);
        if(map.get(endWord) == null)return new ArrayList<List<String>>();
        int graph[][] = new int[len+1][len+1];
        for(int i=0;i<=len;i++){
            for(int j=0;j<=len;j++){
                if(i==j){
                    graph[i][j] = 0;continue;
                }
                if(i == 0){
                    graph[i][j] = distance(beginWord, wordList.get(j-1));
                }else if(j==0){
                    graph[i][j] = 0;
                }else{
                    graph[i][j] = distance(wordList.get(i-1), wordList.get(j-1));
                }
            }
        }
        return bfs(graph, beginWord, len, wordList, endWord);  
    }
    
    public List<List<String>> bfs(int graph[][], String beginWord, int len, List<String> wordList, String endWord){
        List<List<String>> res = new ArrayList<List<String>>();
        
        Queue<List<String>> q = new LinkedList<>();
        List<String> cur = new ArrayList<>();
        cur.add(beginWord);
        q.add(cur);
        
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> vis = new ArrayList<>();
            while(size--!=0){
                List<String> top = q.poll();
                String last = top.get(top.size()-1);
                if(last.equals(endWord)){
                    res.add(top);
                    continue;
                }
                int indx = map.get(last);
                if(visited[indx] == 1)continue;
                vis.add(indx);
                for(int i=0;i<=len;i++){
                    if(graph[indx][i] == 1 && visited[i] == 0){
                        ArrayList<String> temp = new ArrayList<>(top);
                        temp.add(wordList.get(i-1));
                        q.add(temp);
                    }
                }
            }
            for(int x:vis)visited[x]=1;
            if(res.size()!=0)return res;
        }
        return res;
    }
    
    public int distance(String a, String b){
        
        int dist = 0;
        for(int i=0;i<a.length();i++){
            if(a.charAt(i) != b.charAt(i)){
                dist++;
                if(dist>1)return 0;
            }
        }
        return dist;
    }
}
