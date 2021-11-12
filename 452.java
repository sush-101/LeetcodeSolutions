//https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

/*
start from left most ballon.
narrow the range if necessary to accomodate more #balloons.
if a balloon falls out of a range, start a new arrow.
*/

class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (o1,o2)->{
            if(o1[0] > o2[0])return 1;
            return -1; //no need to return o1[0]==o2[0] case as 0 because the order does not matter after we handle o1[0]>o2[0] case.
        });
        int count = 0, left=points[0][0], right=points[0][1];
        
        for(int i=1;i<points.length;i++){
            if(points[i][0] > right){
                count++;
                left = points[i][0];right=points[i][1];
            }else{
                right = Math.min(right, points[i][1]);
                left = points[i][0];
            }
        }
        return count+1;
    }
}
//t.c. - nlogn, s.c - O(1)
