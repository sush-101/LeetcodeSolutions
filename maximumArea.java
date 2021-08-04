//https://leetcode.com/problems/maximal-rectangle/

//APPROACH 1
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0)return 0;
        int r = matrix.length, c = matrix[0].length;
        int arr[] = new int[c];
        int res = 0;
        for(int i=0;i<r;i++){
            int curMax = helper(arr,matrix[i]);
            if(curMax > res)res = curMax;
        }
        return res;
    }
    public int helper(int h[],char row[]){
        int len = row.length;
        for(int i=0;i<len;i++){
            if(row[i] == '1')h[i]++;
            else h[i] = 0;
        }
        return getMaxArea(h);
    }
    
    public int getMaxArea(int arr[]){
        int len = arr.length, left[] = new int[len], right[] = new int[len];
        left[0] = -1;right[len-1]=len;
        int res = 0;
        for(int i=1;i<len;i++){
            if(arr[i] == 0)continue;
            if(arr[i] > arr[i-1])left[i] = i-1;
            else if(arr[i] == arr[i-1])left[i] = left[i-1];
            else{
                int cur = left[i-1];
                while(cur != -1 && arr[cur] >= arr[i]){
                    cur = left[cur];
                }
                left[i] = cur;
            }
        }
        for(int i=len-2;i>=0;i--){
            if(arr[i] == 0)continue;
            if(arr[i] > arr[i+1])right[i] = i+1;
            else if(arr[i] == arr[i+1])right[i] = right[i+1];
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


//APPROACH 2
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0)return 0;
        int r = matrix.length, c = matrix[0].length, heights[] = new int[c], left[] = new int[c],right[] = new int[c], res = 0;
        Arrays.fill(right, c);
        
        for(int i=0;i<r;i++){
            int cur_left = 0, cur_right = c;
            for(int j=0;j<c;j++){
                if(matrix[i][j] == '1'){
                    heights[j]++;
                    left[j] = Math.max(left[j], cur_left);
                }
                else{
                    heights[j] = 0;
                    left[j] = 0;
                    cur_left = j+1;
                }
            }
            
            for(int j=c-1;j>=0;j--){
                if(matrix[i][j] == '1'){
                    right[j] = Math.min(right[j], cur_right);
                }else{
                    right[j] = c;
                    cur_right = j;
                }
                res = Math.max(res, (right[j]-left[j])*heights[j]);
            }
        }
        return res;
    }
}
