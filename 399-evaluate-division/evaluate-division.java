class Pair<K,V>{
    K key;
    V value;
    Pair(K key, V value){
        this.key = key;
        this.value = value;
    }
    K getKey(){
        return key;
    }
    V getValue(){
        return value;
    }
}
class Solution {
    HashMap<String, ArrayList<Pair<String, Double>>> graph;
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        graph = new HashMap();
        for(int i=0;i<equations.size();i++){
            String left = equations.get(i).get(0);
            String right = equations.get(i).get(1);
            double val = values[i];
            graph.putIfAbsent(left, new ArrayList<>());
            graph.putIfAbsent(right, new ArrayList<>());
            ArrayList<Pair<String, Double>> cur = graph.get(left);
            cur.add(new Pair(right, val));
            graph.get(right).add(new Pair(left, 1/val));
        }
        int resLen = queries.size();
        double res[] = new double[resLen];
        for(int i=0;i<resLen;i++){
            res[i] = bfs(queries.get(i).get(0), queries.get(i).get(1));
        }
        return res;
    }
    public double bfs(String src, String dest){
        if(src.equals(dest) && graph.get(src)!=null)return 1d;
        Queue<Pair<String, Double>> queue = new LinkedList<>();
        Set<String> vis = new HashSet<>();
        queue.add(new Pair(src, 1.0));
        vis.add(src);
        double res = 1.0;
        while(!queue.isEmpty()){
            Pair<String, Double> cur = queue.poll();
            String v = cur.getKey();
            Double val = cur.getValue();
            ArrayList<Pair<String, Double>> links = graph.get(v);
            if(links == null)continue;
            for(Pair<String, Double> link : links){
                if(vis.contains(link.getKey()))continue;
                vis.add(link.getKey());
                queue.add(new Pair(link.getKey(), link.getValue()*val));
                if(link.getKey().equals(dest))
                    return link.getValue()*val;
            }
        }
        return -1d;
    }
}