//https://leetcode.com/problems/evaluate-division/

//dfs - T.C - O(V+E), Space - O(E)

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, ArrayList<Edge>> graph = new HashMap<>();
        
        for(int i=0;i<equations.size();i++){
            List<String> equation = equations.get(i);
            addEdge(equation.get(0), new Edge(equation.get(1), values[i]),
                           graph);
            addEdge(equation.get(1), new Edge(equation.get(0), 1/values[i]),
                           graph);     
        }
        
        for(Map.Entry<String, ArrayList<Edge>> entry: graph.entrySet()){
            addEdge(entry.getKey(), new Edge(entry.getKey(),1), graph);
        }
        
        int n = queries.size();
        double[] res = new double[n];
        
        for(int i=0;i<n;i++){
            res[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), graph, new HashSet<String>());
        }
        return res;
    }
    
    private double dfs(String a, String b, HashMap<String, ArrayList<Edge>> graph, HashSet<String> vis){
        vis.add(a);
        ArrayList<Edge> cur = graph.get(a);
        if(cur == null)return -1;
        if(a.equals(b))return 1;
        for(Edge e: cur){
            if(vis.contains(e.value))continue;
            if(e.value.equals(b))return e.weight;
            double temp = dfs(e.value,b,graph,vis);
            if(temp!=-1)return e.weight*temp;
        }
        vis.remove(a);
        return -1;
    }
    
    private void addEdge(String vertex, Edge e, HashMap<String, ArrayList<Edge>> graph){
        ArrayList<Edge> cur = graph.getOrDefault(vertex, new ArrayList<>());
        cur.add(e);
        graph.put(vertex, cur);
    }
}
class Edge{
    double weight;
    String value;
    Edge(String value, double weight){
        this.value = value;
        this.weight = weight;
    }
}
