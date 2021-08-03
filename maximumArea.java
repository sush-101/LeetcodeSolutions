//https://leetcode.com/problems/maximal-rectangle/

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0)return 0;
        int r = matrix.length, c = matrix[0].length;
        int arr[][] = new int[r][c];
        
        
        //calculate height
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(matrix[i][j] == '1'){
                    arr[i][j] = 1;
                    if(i > 0){
                        arr[i][j] += arr[i-1][j];
                    }
                }else arr[i][j] = 0;
            }
        }
        
        int res = 0;
      
        //calculate max area
        for(int i=0;i<r;i++){
            res = Math.max(res, getMaxArea(arr[i]));
        }
        return res;
        
    }
    public int getMaxArea(int arr[]){
        int len = arr.length, left[] = new int[len], right[] = new int[len];
        left[0] = -1;right[len-1]=len;
        int res = 0;
      
        //left most index where elements after left are >= arr[i]
        for(int i=1;i<len;i++){
            if(arr[i] > arr[i-1])left[i] = i-1;
            else{
                int cur = left[i-1];
                while(cur != -1 && arr[cur] >= arr[i]){
                    cur = left[cur];
                }
                left[i] = cur;
            }
        }
      
        //right most index where elements before right are >= arr[i]
        for(int i=len-2;i>=0;i--){
            if(arr[i] > arr[i+1])right[i] = i+1;
            else{
                int cur = right[i+1];
                while(cur != len && arr[cur] >= arr[i]){
                    cur = right[cur];
                }
                right[i] = cur;
            }
            res = Math.max(res, arr[i]*(right[i]-left[i]-1));
        }
        res = Math.max(res, arr[len-1]*(right[len-1]-left[len-1]-1));
        return res;
    }
}
