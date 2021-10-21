//https://leetcode.com/problems/remove-k-digits/
class Solution {
    public String removeKdigits(String num, int k) {
        char arr[] = num.toCharArray();
        StringBuilder sb = new StringBuilder();
        int n = arr.length, nextSmaller[] = new int[n];
        for(int i=n-1;i>=0;i--){
            int j = i+1;
            while(j<n && j>=0 && arr[j] >= arr[i] ){j = nextSmaller[j];}
            if(j >= n || j<0)
                nextSmaller[i] = -1;
            else nextSmaller[i] = j;
        }
        
        for(int i=0;i<n;i++){
            if(n-i == k){
                k--;
            }else if(nextSmaller[i] != -1 && nextSmaller[i] - i <= k){
                k--;    
            } 
            else sb.append(arr[i]);
        }
        
        if(sb.length() == 0)return "0";
        while(sb.length()>1 && sb.charAt(0)=='0'){sb.deleteCharAt(0);}
        return sb.toString();
    }
}
/*
greedy
find next smaller element.
if it is at distance < k , then remove this digit.
if #digits from this digit == k, remove this digit
T.C - O(n)
S.C - O(n)
*/
