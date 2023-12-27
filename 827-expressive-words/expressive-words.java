
class Solution {
    ArrayList<Pair<Character, Integer>> al;
    public int expressiveWords(String s, String[] words) {
        al = new ArrayList();
        int res = 0, i = 0;
        char[] arr = s.toCharArray();
        while(i<arr.length){
            char cur = arr[i];
            int count = 0;
            while(i < arr.length && arr[i] == cur){
                i++;
                count++;
            }
            al.add(new Pair(cur, count));
        }
        // System.out.println(al);
        for(int j=0;j<words.length;j++){
            if(isStretchy(words[j])){
                res++;
            }
        }
        return res;
    }
    boolean isStretchy(String word){
        char[] arr1 = word.toCharArray();
        // System.out.println(arr1);
        int pos = 0, i = 0;
        while(i<arr1.length  && pos<al.size()){
            char cur = arr1[i];
            int count = 0;
            // System.out.println(" i " + i + " char "+cur + " target "+ al.get(pos).getKey());
            if(cur != al.get(pos).getKey())return false;
            while(i < arr1.length && arr1[i] == cur){
                i++;
                count++;
            }
            
            // System.out.println(" count "+count + " target " + al.get(pos).getValue());
            if(count == al.get(pos).getValue()){
                pos++;
                continue;
            }
            if(al.get(pos).getValue() < count || al.get(pos).getValue() <3)return false;
            pos++;
        }
        return i==arr1.length && pos==al.size();
    }
}
class Pair<K,V>{
    char K;
    int V;
    Pair(char K, int V){
        this.K = K;
        this.V = V;
    }
    char getKey(){
        return K;
    }
    int getValue(){
        return V;
    }
    @Override
    public String toString(){
        return K + " " + V;
    }
}