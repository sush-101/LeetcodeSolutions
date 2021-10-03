//Approach1: tc - O(n)
class Solution {
    public int minOperations(int n) {
        int i = 0, j = n-1, res = 0;
        while(i<j){
            res += j-i;
            j--;i++;
        }
        return res;
    }
}

//Approach2: simplify the above expression. tc-O(1)
class Solution {
    public int minOperations(int n) {
        int res = sigma(n-1)-2*sigma((n-2)/2);
        if((n&1)==1){
            res -= n/2;
        }
        return res;
    }
    private int sigma(int n){
        return n*(n+1)/2;
    }
}

//https://leetcode.com/problems/minimum-operations-to-make-array-equal/
