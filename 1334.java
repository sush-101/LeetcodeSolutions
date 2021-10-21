//https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int min = Integer.MAX_VALUE, ans = n-1;
        ArrayList<Node> arr[] = new ArrayList[n];
        for(int i=0;i<n;i++){
            arr[i] = new ArrayList<Node>(); 
        }
        
        for(int x[]: edges){
            int a = x[0], b = x[1], c = x[2];
            arr[a].add(new Node(b,c));
            arr[b].add(new Node(a,c));
        }
        
        for(int i=n-1;i>=0;i--){
            int temp = runDijkstra(arr, i, distanceThreshold);
            if(temp < min){
                min = temp;
                ans = i;
            }
        }
        return ans;
    }
    
    private int runDijkstra(ArrayList<Node> arr[], int cur, int distanceThreshold){
        int n = arr.length, minDist[] = new int[n], count = 0;
        Set<Integer> set = new HashSet<>();
        Arrays.fill(minDist, Integer.MAX_VALUE);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->
                                                        minDist[o1]-minDist[o2]);
        
        minDist[cur]=0;pq.add(cur);
        
        while(set.size()<n && !pq.isEmpty()){
            int i = pq.poll();
            if(set.contains(i))continue;
            set.add(i);
            if(minDist[i] > distanceThreshold)
                return count-1;
            count++;
            for(Node x: arr[i]){
                if(set.contains(x.value))continue;
                int t = minDist[i]+x.weight;
                if(minDist[x.value] > t){
                    minDist[x.value] = t;
                    pq.add(x.value);
                }
            }
        }
        return count-1;
    }
}
class Node{
    int value, weight;
    Node(int value, int weight){
        this.value = value;
        this.weight = weight;
    }
}

/*
min distance between each pair of vertices using Dijkstra.
T.C - V*Vlog(E)
S.C - O(V+E) (for hashset, minDist, adjacency list)
*/
