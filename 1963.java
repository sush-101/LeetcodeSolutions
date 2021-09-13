//https://leetcode.com/problems/add-minimum-number-of-rungs/

/*
___________________________
Brute force :
We add one rung at a time until next rung is at distance > dist
T.C - O(range/dist)

__________________________
Efficient:
if the next rung is at distance > dist
  we add #rungs such that the last rung we add is < next rung
T.C - O(n)
S.C - O(1)

___________________________
Eg. if rungs = [1, 6, 1000000], dist = 2
Efficient = 2 units
Brute force = (1000000 - 1) / 2
*/


class Solution {
    public int addRungs(int[] rungs, int dist) {
        int rungToAdd = 0;
        int cur = 0, next = -1;
        while(++next < rungs.length){
            if(rungs[next] - cur > dist){
                rungToAdd += (int)Math.ceil(((double)rungs[next] - cur)/dist)-1;
            }
            cur = rungs[next];
        }
        return rungToAdd;
    }
}
