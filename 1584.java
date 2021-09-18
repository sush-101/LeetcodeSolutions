//https://leetcode.com/problems/min-cost-to-connect-all-points/
/*
start from a point, consider this as one end of the line -> (x1,y1)
update distances - if |x1-currentx| + |y1-currenty| < existing distance
repeat this until all points are connected.
T.C - O(n^2)
S.C - O(n)
*/
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length,count = 0, dist[] = new int[n], 
        curx = points[0][0], cury = points[0][1];
        
        boolean vis[] = new boolean[n];
        Arrays.fill(dist, 4000000);
        int res = 0;
        vis[0] = true;
        
        while(count++<n-1){ 
            int min = 4000000, visi = 0;
            
            for(int i=0;i<n;i++){
                if(vis[i])continue;
                int temp = 
                    Math.abs(points[i][0]-curx) + Math.abs(points[i][1]-cury);
                
                if(dist[i] > temp){
                    dist[i] = temp;
                } 
                if(min > dist[i]){
                   visi = i;
                   min = dist[i];
                }
            }
            
            res += min;
            curx = points[visi][0];cury=points[visi][1];
            vis[visi] = true;
        }
        return res;
    }
}

/*
[[0,0],[2,2],[3,10],[5,2],[7,0]]
[[0,0],[1,1],[1,0],[-1,1]]
[[-1000000,-1000000],[1000000,1000000]]
*/
