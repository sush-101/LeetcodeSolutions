//https://leetcode.com/problems/reconstruct-itinerary/
//Approach1
/*backtrack - all possibilities, greedy*/

class Solution {
    List<String> route = new ArrayList<>();
    HashMap<String, List<String>> map = new HashMap<>();
    HashMap<String, boolean[]> visited = new HashMap<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        for(List<String> list: tickets){
            List<String> temp;
            temp = map.getOrDefault(list.get(0), new ArrayList<String>());
            temp.add(list.get(1));
            map.put(list.get(0), temp);
        }
        for(Map.Entry<String, List<String>> e:map.entrySet()){
            List<String> temp = e.getValue();
            Collections.sort(temp);
            map.put(e.getKey(), temp);
            visited.put(e.getKey(), new boolean[e.getValue().size()]);
        }
        route.add("JFK");
        backTrack("JFK", tickets.size());
        return route;
    }
    private boolean backTrack(String cur, int n){
        if(route.size() == n+1)return true;
        
        if(map.get(cur) == null)return false;
        int i = 0;
        boolean[] temp = visited.get(cur);
        
        for(String next: map.get(cur)){
            if(temp[i]){i++;continue;}
            route.add(next);
            temp[i] = true;
            if(backTrack(next, n))return true;
            route.remove(route.size()-1);
            temp[i] = false;
            i++;
        }
        return false;
    }
}

//Approach 2
//DFS

class Solution {
    List<String> route = new ArrayList<>();
    HashMap<String, List<String>> map = new HashMap<>();
    HashMap<String, boolean[]> visited = new HashMap<>();
    
    public List<String> findItinerary(List<List<String>> tickets) {
        for(List<String> list: tickets){
            List<String> temp;
            temp = map.getOrDefault(list.get(0), new ArrayList<String>());
            temp.add(list.get(1));
            map.put(list.get(0), temp);
        }
        for(Map.Entry<String, List<String>> e:map.entrySet()){
            List<String> temp = e.getValue();
            Collections.sort(temp);
            map.put(e.getKey(), temp);
            visited.put(e.getKey(), new boolean[e.getValue().size()]);
        }
        backTrack("JFK", tickets.size());
        Collections.reverse(route);
        return route;
    }
    
    private void backTrack(String cur, int n){
        if(map.get(cur) == null){
            route.add(cur);
            return;
        }
        boolean vis[] = visited.get(cur);
        int i = 0;
        
        for(String next: map.get(cur)){
            if(vis[i]){i++;continue;}
            vis[i] = true;
            backTrack(next, n);
            i++;
        }
        route.add(cur);
        
    }
}
