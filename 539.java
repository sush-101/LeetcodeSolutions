//https://leetcode.com/problems/minimum-time-difference/

/*Approach 1:
Sort the list. Compare each consecutive times. Compare last and first times.

T.c: O(n*logn)
S.C: O(1)
*/

class Solution {
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        
        int res = 1440;
        System.out.println(timePoints);
        for(int i=0;i<timePoints.size();i++){
            res = Math.min(res,
                           getDif(timePoints.get(i),
                                  timePoints.get((i+1)%timePoints.size())));
        }    
        return res;
    }
    
    private int getDif(String a, String b){
        
        int x = 60*Integer.parseInt(a.substring(0,2))+
                    Integer.parseInt(a.substring(3));
        int y = 60*Integer.parseInt(b.substring(0,2))+
                    Integer.parseInt(b.substring(3));
        
        if(x>y)return 1440 - x + y;
        return y-x;
    }
}

/*Approach 2
As 2 <= timePoints.size() <= 2 * 104, there can be duplicates. Keep note if a time is present.
T.C - O(1), space - O(1)
*/

class Solution {
    public int findMinDifference(List<String> timePoints) {
        boolean mark[] = new boolean[24*60+1];
        
        for(String str: timePoints){
            int time = 60*Integer.parseInt(str.substring(0,2))+
                        Integer.parseInt(str.substring(3));
            if(mark[time])return 0;
            mark[time] = true;
        }
        
        int prev = -1, first = -1, res = 1440;
        for(int i=0;i<=24*60;i++){
            if(!mark[i])continue;
            if(prev != -1) res = Math.min(res,i-prev);
            else first = i;
            prev = i;
        }
        
        res = Math.min(res, 1440-prev+first);
        return res;
    }
}
