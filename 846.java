//https://leetcode.com/problems/hand-of-straights/

/*
using map to group the cards by their value i.e hand[i].
in a window of size groupSize, all the hand[i] value should be same.
for sorting we use treemap.
let x be cardtypes
t.c. : (for sorting treemap: xlogx) + (for iterating: x*groupSize)
s.c. : x
*/

class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        Map<Integer, Integer> map = new TreeMap<>();
        for(int x:hand){map.put(x, map.getOrDefault(x,0)+1);}
        int cardtypes = map.size();
        
        for(int x: map.keySet()){
            if(groupSize > cardtypes)return false;
            int size = map.get(x);
            for(int i=0;i<groupSize && size>0;i++){
                if(map.getOrDefault(x+i,0) < size)return false;
                map.put(x+i, map.get(x+i)-size);
                if(map.get(x+i) == 0)cardtypes--;
            }
            if(cardtypes==0)return true;
        }
        return true;
    }
}
