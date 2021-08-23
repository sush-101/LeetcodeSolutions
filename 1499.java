https://leetcode.com/problems/max-value-of-equation/

/*
yi + yj + |xi - xj| = yi-xi + xj+yj

At an index j, we do xi+yj and find the max (yi-xi) which is on left side.
we use pointer 'left' to keep track of max (yi-xi) i.e from 'right' towards left side until 'left' pointer, value (yi-xi) at 'left' will be max.

we move 'left' when (yi-xi) at 'left' < (yi-xi) at 'right'

T.C = 2*O(n), space = constant

We can also use maxheap to keep track of max (yi-xi) for points already seen.
*/

class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
        int left = 0, right = 0, n = points.length, res = Integer.MIN_VALUE;
        while(right < n){
            if(left == right){
                right++;continue;
            }
            if(points[right][0] - points[left][0] > k){
                left++;continue;
            }
            res = Math.max(res, points[right][1] + points[right][0] + 
                                points[left][1] - points[left][0]);
            
            //difference at this index is less compared to  difference at left pointer, so this index can be ignored
            
            if(points[right][1] - points[right][0] >= 
               points[left][1] - points[left][0]){
                left++;
                continue;
            }
            right++;
        }
        right--;
        while(left<right){
            res = Math.max(res, points[right][1] + points[right][0] + 
               points[left][1] - points[left][0]);
            left++;
        }
        return res;
    }
}
