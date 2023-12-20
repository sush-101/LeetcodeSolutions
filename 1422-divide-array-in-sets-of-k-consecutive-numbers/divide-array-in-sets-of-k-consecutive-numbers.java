class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap();
        for(int num: nums){
            map.put(num, map.getOrDefault(num,0)+1);
        }
        while(map.size()!=0){
            // for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            //     System.out.println(entry.getKey() + " " + entry.getValue());
            // }
            // System.out.println("--------------");
            int count = 0;
            int prev = -1;
            int freq = -1;
            HashMap<Integer, Integer> temp = new HashMap();
            for(Map.Entry<Integer, Integer> entry: map.entrySet()){
                if(entry.getValue() == 0)continue;
                if(prev == -1){
                    prev = entry.getKey();
                    freq = entry.getValue();
                    temp.put(entry.getKey(), entry.getValue()-freq);
                    // System.out.println("freq "+freq);
                }else{
                    if(entry.getKey() != prev+1)return false;
                    if(entry.getValue() < freq)return false;
                    prev = prev+1;
                    temp.put(entry.getKey(), entry.getValue()-freq);
                }
                count++;
                if(count == k)break;
            }
            // System.out.println("count "+count);
            if(count == 0) return true;
            if(count != k)return false;
            map.putAll(temp);
        }
        return true;
    }
}