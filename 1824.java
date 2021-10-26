//https://leetcode.com/problems/minimum-sideway-jumps/
/*
T.c. O(n)
s.c - O(1)

we start from destination move towards start. if at a point there is an obstacle, make that point as MAX no of steps.
we copy the value from prev to cur. then if cur is max , it can mean there is blocker in this row or prev row.
if the blocker is in cur row , do nothing. if blocker is in prev row only then look sidewards.
*/
class Solution {
    public int minSideJumps(int[] obstacles) {
        int prevRow[] = new int[3], n = obstacles.length, max = Integer.MAX_VALUE;
        
        for(int i=n-1;i>=0;i--){
            int curRow[] = prevRow;
            if(obstacles[i]!=0)curRow[obstacles[i]-1]=max;
            for(int j=0;j<3;j++){
                if(curRow[j]==max && j!=obstacles[i]-1){
                    curRow[j] = 1 + Math.min(curRow[(j+1)%3], curRow[(j+2)%3]);
                }
            }
            prevRow = curRow;
        }
        return prevRow[1];
    }
}

