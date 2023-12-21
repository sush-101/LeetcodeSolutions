class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int prefixSum[] = new int[n+1];
        int suffixSum[] = new int[n+1];
        for(int i=1;i<=n;i++){
            prefixSum[i] = cardPoints[i-1] + prefixSum[i-1];
        }
        for(int i=n-1;i>=0;i--){
            suffixSum[i] = cardPoints[i] + suffixSum[i+1];
        }
        
        int left = 0, right = n-1, score = 0, targetLength = n-k;
        while(left <= right && (right - left + 1) > targetLength){
            int leftSum = prefixSum[left+k] - prefixSum[left];
            int rightSum = suffixSum[right-k+1] - suffixSum[right+1];
            // System.out.println("k " + k + "leftSum "+ leftSum + " rightSum " + rightSum);
            if(leftSum > rightSum){
                score += cardPoints[left];
                left++;
            }else{
                score += cardPoints[right];
                right--;
            }
            k--;
        }
        return score;
    }
}