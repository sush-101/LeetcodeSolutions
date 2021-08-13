//https://leetcode.com/problems/can-i-win/

/*
2^maxChoosableInteger possibilites.
Used memoization
*/

class Solution {
    HashMap<Integer, Boolean> map = new HashMap<>();
    
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        
        if(maxChoosableInteger >= desiredTotal)return true;
        return checkPossibility(0, maxChoosableInteger, desiredTotal);

    }

    public boolean checkPossibility(int prev, int max, int target){
        
        if(max*(max+1)/2 < target)return false;
        
        for(int i=1;i<=max;i++){
        
            int cur = 1<<(i-1);
            if((cur&prev) == 0){
                cur = cur|prev;
                if(target - i <= 0){
                    map.put(prev,true);return true;
                }
                if(map.get(cur) == null)checkPossibility(cur, max, target-i);
                if(map.get(cur) == false){
                        map.put(prev, true);
                        return true; 
                }
            }
        }
        
        map.put(prev,false);
        return false;
    }
    
   
}
