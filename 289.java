//https://leetcode.com/problems/game-of-life/

class Solution {
    public void gameOfLife(int[][] board) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                int getCount = noOfNeighbors(i,j,board);
                if((board[i][j]&1) == 1){
                    if(getCount == 2 || getCount == 3)
                        board[i][j] = 3;
                }else{
                    if(getCount == 3)
                        board[i][j] = 2; 
                }
            }
        }
        fillNextState(board);
    }
    private int noOfNeighbors(int x, int y, int [][]board){
        int dir[] = {0,1,0,-1,1,1,-1,-1,0}, m = board.length, n = board[0].length,
            count = 0;
        
        for(int i=0;i<8;i++){
            int x1 = x+dir[i], y1 = y+dir[i+1];
            if(x1>=0 && x1<m && y1>=0 && y1<n && (board[x1][y1]&1)==1){
                count+=1;
            }
        }
        return count;
    }
    private void fillNextState(int[][] board){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                board[i][j] >>= 1;
                board[i][j] &= 1;
            }
        }
    }
}

/*
T.C - O(mn)
Space - O(1)
*/
