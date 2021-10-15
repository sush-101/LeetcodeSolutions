//https://leetcode.com/problems/shortest-path-visiting-all-nodes/
class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length, res = Integer.MAX_VALUE, dp[][] = new int[n][1<<n];;
        Queue<PathTraversed> q = new LinkedList<>();
 
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            q.add(new PathTraversed(1<<i, i));
            dp[i][1<<i] = 0;
        }
        
        while(!q.isEmpty()){
            int size = q.size();
            while(size--!=0){
                PathTraversed p = q.poll();
                int curNode = p.endNode, curMask = p.mask;
                    for(int nextNode: graph[curNode]){
                        int nextMask = (p.mask | (1<<nextNode));
                        if(dp[nextNode][nextMask] > dp[curNode][curMask]+1){
                            if(nextMask == (1<<n - 1))return dp[curNode][curMask]+1;
                            dp[nextNode][nextMask] = dp[curNode][curMask]+1;
                            q.add(new PathTraversed(nextMask, nextNode));
                        }
                    }
            }
        }
    
        for(int i=0;i<n;i++){
            res = Math.min(dp[i][(1<<n)-1], res);
        }
        return res;
    }
}
class PathTraversed{
    int mask, endNode;
    PathTraversed(int mask, int endNode){
        this.mask = mask;
        this.endNode = endNode;
    }
}
/*
The path can start from any node.
  From a node, we can choose any adjacent edge and have to come back to this node again if there are any unvisited node.
  We traverse the path whose 2 way distance is more at the end.
  We use DP to store the least distance required to reach i node covering xyz.. nodes -> dp[i][xyz..]
Result is least dp[endNode][all Nodes covered]
*/
