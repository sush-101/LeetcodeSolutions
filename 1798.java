class Solution {
    public int getMaximumConsecutive(int[] coins){
        int max = 0;
        Arrays.sort(coins);
        for(int x:coins){
            if(x <= max+1)max += x;
            else return max+1;
        }
        return max+1;
    }
}
/*
https://leetcode.com/problems/maximum-number-of-consecutive-values-you-can-make/

Approach1: Add current coin to all possible sums previously found. Store in a treeset.
Break when condition of consecutive number is violated.
T.C - O(n^2)
S.C - O(2*n)

Approach2: 
Sort the array - because we pick smallest at each step.
If there are 0,1,2...x consecutive numbers possible, in the next step the number has to be x+1 or less.
We can simoly add 0,1,2,...x we got in previous step to a number <=x+1 which will result in consecutive numbers.
T.C - O(nlogn)
S.C - O(1)


[6,7]
[1,1,1,4]
*/
