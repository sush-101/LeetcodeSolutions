/* Approach 1:

precompute left, right sums.
For i=0;i<=k;i++
  From left end : take i, From right end: take k-i elements.
  *Use precomputed results.
  Keep track of MAX SUM.
  
T.C. Precomputation: 2*(len of array) + K (For loop)
    = 2*len + K ~ 3ms 
Space: 2*(len of array)
*/

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int max = 0, len = cardPoints.length;
        int left[] = new int[len], right[] = new int[len];
        
        for(int i=0;i<len;i++){
            left[i] += i>0?left[i-1]+cardPoints[i]:cardPoints[i];
        }
        for(int i=len-1;i>=0;i--){
            right[i] = i<len-1?cardPoints[i]+right[i+1]:cardPoints[i];
        }
        
        for(int count=0;count <= k;count++){
            int ans = 0;
            
            ans += count==0?0:left[count-1];
            
            ans += k-count==0?0:right[len-k+count];
            
            if(max < ans) max = ans; 
        }
        return max;
        
    }
}

/*
Approach 2:

Find window of size len-k with minimum sum.
T.C - O(len of array) ~ 2ms
Space: 0(1)
*/

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length, window_len = len-k, min = 0;
        
        int window = 0, sum = 0, left = 0;
        
        for(int right = 0; right < len; right++){
            
            if(right < window_len){
                window += cardPoints[right];
            }
            
            if(right == window_len){
                sum = window;
                min = window;
            }
            
            if(right >= window_len){
                window = window - cardPoints[left++] + cardPoints[right];
                min = Math.min(min, window);
                sum += cardPoints[right];
            }
        }  
        
        return sum - min;
        
    }
}
