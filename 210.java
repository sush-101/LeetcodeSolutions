//https://leetcode.com/problems/course-schedule-ii/
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer> arr[] = new ArrayList[numCourses];
        int indegree[] = new int[numCourses];
       
        int res[] = new int[numCourses], k = 0;
        Queue<Integer> q = new LinkedList<>();
        
        for(int[] x: prerequisites){
            if(arr[x[1]] == null)arr[x[1]] = new ArrayList<>();
            arr[x[1]].add(x[0]);
            indegree[x[0]]++;
        }
        
        for(int i=0;i<numCourses;i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }
        
        while(!q.isEmpty()){
            int cur = q.poll();
            res[k++] = cur;
            if(arr[cur] == null){
                continue;
            }
            for(int x: arr[cur]){
                indegree[x]--;
                if(indegree[x] == 0)
                    q.add(x);
            } 
        }
        if(k!=numCourses)return new int[0];
        return res;
    }
}

/*
Detect a cycle - similar to deadlock situation
Topological sort.
T.C- O(V+E)
S.C-O(V+E)
*/
