//https://leetcode.com/problems/maximum-compatibility-score-sum/

/*
Max number of mentors/students is 8. So can be done in 8!
Can be optimized using dp.
Eg. when we have assigned mentors to first 3 students to lets say first 3 mentors,
function call will be findAllPossibility(students, mentors, indx = 4, 00000111)
storing the result we get in backtracking from this point in dp array [4][00000111]..
00000111 this point can be reached in 3! ways...
so next time, when we get findAllPossibility(students, mentors, indx = 4, 00000111) it can be fetched from dp.

T.C - Σ nCx
      x from 1 to n
       => 2^n
       
S.C - O(n*2^n)
*/

class Solution {
    int dp[][];
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        dp = new int[students.length][(int)Math.pow(2, mentors.length)-1];
        return findAllPossibility(students, mentors, 0, 0);
        
    }
    private int findAllPossibility(int [][]students, int[][] mentors, int indx, int mask){
    
        int local = 0;
        if(indx == students.length){
            return 0;
        }
        if(dp[indx][mask] != 0)return dp[indx][mask];
        for(int i=0;i<mentors.length;i++){
            int shift = 1<<i;
            if((mask&shift) != 0)continue;
            int temp = findScore(students[indx], mentors[i]) + findAllPossibility(students, mentors, indx+1, (mask|shift));
            local = Math.max(local, temp);
        }
        dp[indx][mask] = local;
        return local;
    } 
    private int findScore(int a[], int b[]){
        int count = 0;
        for(int i=0;i<a.length;i++){
            if(a[i] == b[i])count++;
        }
        return count;
    }
}
/*
[[1,1,0],[1,0,1],[0,0,1]]
[[1,0,0],[0,0,1],[1,1,0]]
[[0,0],[0,0],[0,0]]
[[1,1],[1,1],[1,1]]
*/
