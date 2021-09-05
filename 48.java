//https://leetcode.com/problems/rotate-image/
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;//temp[] = new int[n];
        int top = 0, bottom = n-1, left = 0, right = n-1;
        
        while(top < bottom){
            for(int k = 0; k < right-left; k++){
                //store left
                int t = matrix[bottom-k][left];
                //shift bottom to left
                matrix[bottom-k][left] = matrix[bottom][right-k];
                //shift right to bottom
                matrix[bottom][right-k] = matrix[top+k][right];
                //shift top to right
                matrix[top+k][right] = matrix[top][left+k];
                //shift left to top
                matrix[top][left+k] = t;  
            } 
            top++;bottom--;left++;right--;
        }
        
    }
}

/*
t.c - O(n^2)
s.c - O(1)
*/
