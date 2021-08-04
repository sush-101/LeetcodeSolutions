//Brute Force: recursion without memoization: T.C. 2^(m+n) where m=len of s, n=len of t

//EFFICIENT - O(m*n)-----------------------
//DP based solution

class Solution {
    public int numDistinct(String s, String t) {
        int r = s.length(), c = t.length();
        int dp[][] = new int[r][c];
        char s_arr[] = s.toCharArray(), t_arr[] = t.toCharArray();
      
        /* start from the end of two strings.
           No of subsequences s.substring(i) has of t.substring(j) is sum of below two:
              1. if s[i]=t[j] -> no of subsquences s.substring(i+1) has of t.substring(j+1)
              2. no of subsquences s.substring(i+1) has of t.substring(j) 
           So, use dp to store results
              */
        
        for(int i=r-1;i>=0;i--){
            for(int j=c-1;j>=0;j--){
                if(s_arr[i] == t_arr[j]){
                    dp[i][j] += (i+1<r && j+1<c) ? dp[i+1][j+1] : (j==c-1)?1:0;
                }
                dp[i][j] += (i+1<r)? dp[i+1][j] : 0;
            }
        }
        return dp[0][0];
    }
}
// T.C = O(m*n) and Space = O(m*n), where m=s.length(), n=t.length()

// Recursion and memoization

/* Case 1: if s_arr[i] == t_arr[j] , find from i+1,j+1
  Common case: find from i+1,j
  Add both.
*/
class Solution {
    int matrix[][];
    public int numDistinct(String s, String t) {
        int r = s.length()+1, c = t.length()+1;
        matrix = new int[r][c];
        for(int i=0;i<r;i++)Arrays.fill(matrix[i],-1);
        char s_arr[] = s.toCharArray(), t_arr[] = t.toCharArray();
        recur(s_arr,t_arr,0,0);
        return matrix[0][0];
    }
    public void recur(char s_arr[], char t_arr[], int pos1, int pos2){
        if(pos2 == t_arr.length){
            matrix[pos1][pos2] = 1;
            return;
        }
        if(pos1 == s_arr.length){
            matrix[pos1][pos2] = 0;
            return;
        }
        int ans = 0;
        if(s_arr[pos1] == t_arr[pos2]){
            if(matrix[pos1+1][pos2+1] == -1)
                recur(s_arr,t_arr,pos1+1,pos2+1);
            ans += matrix[pos1+1][pos2+1];
        }
        if(matrix[pos1+1][pos2] == -1)
            recur(s_arr,t_arr,pos1+1,pos2);
        ans += matrix[pos1+1][pos2];
        matrix[pos1][pos2] = ans;
    }  
}
//T.C - O(m*n), Space =stack space= O(MIN(m,n)) where m = length of s, n=len of t
