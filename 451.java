//https://leetcode.com/problems/sort-characters-by-frequency/
class Solution {
    public String frequencySort(String s) {
        int count[] = new int[123],resIndx = 0;
        char res[] = new char[s.length()];

        for(char x:s.toCharArray()){
            count[x]++;
        }
        while(true){
            int max = 0, indx = -1;
            for(int i=0;i<123;i++){
                if(count[i] > max){
                    max = count[i];
                    indx = i;
                }
            }
            if(indx == -1)return new String(res);
            while(count[indx]-- != 0){
                res[resIndx++] = (char)indx;
            }
        }
    }
}

//......................................................//

class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        ArrayList<Character> al = new ArrayList<>();
        for(char x: s.toCharArray()){
            map.put(x, map.getOrDefault(x,0)+1);
            if(map.get(x) == 1){
                al.add(x);
            }
        }
        Collections.sort(al, (o1,o2)->map.get(o2) - map.get(o1));
        StringBuilder sb = new StringBuilder("");
        for(char x:al){
            int count = map.get(x);
            while(count-- > 0){
                sb.append(x);
            }
        }
        return sb.toString();
    }
}
