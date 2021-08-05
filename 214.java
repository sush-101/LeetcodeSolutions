/* Idea: find the longest palindrome starting from index 0 because you add characters at the beginning.*/

// T.C - O(n^2)
/* An index can be a mid of a palindrome with one of its end at index 0.
So, iterate i from an mid of the string to 0, check if i is mid of the palindrome.
*/

class Solution {
    public String shortestPalindrome(String s) {
        if(s == null || s.length() == 0)return "";
        int len = s.length(), mid = (len-1)/2, pos = 1;
        char s_arr[] = s.toCharArray();
        for(int i=mid;i>=0;i--){
            if(2*(i+1) < len && isEquals(s_arr, i, i+1)){
                pos = 2*(i+1);
                break;
            }
            
            if(i==0 || isEquals(s_arr, i-1, i+1)){
                pos = 2*i+1;
                break;
            }
        }
        String temp = s.substring(pos);
        StringBuilder sb = new StringBuilder(temp);
        return sb.reverse().toString()+s;
    }
    public boolean isEquals(char s_arr[], int i, int j){
        while(i>=0){
            if(s_arr[i] != s_arr[j])return false;
            i--;j++;
        }
        return true;
    }
}



//Linear time
/*
If we reverse a string which is a palindrome, it will be same as the original string.
The longest palindrome starting from index 0, after we reverse it and append at the end of the string -> its length can be found out
by finding the longest suffix at end which is also a prefix.
To fill the KMP - T.C - O(n)
*/

class Solution {
    public String shortestPalindrome(String s) {
        String new_s = new StringBuilder(s).append('.').append(new StringBuilder(s).reverse().toString()).toString();
        int n_len = new_s.length(), kmp[] = new int[n_len];
        char new_str[] = new_s.toCharArray();
        int j = 0;
        kmp[0] = 0;
        for(int i=1;i<n_len;i++){
            while(j>0 && new_str[i] != new_str[j]){
                j = kmp[j-1];
                if(j == 0)break;
            }
            if(new_str[i] == new_str[j]){
                kmp[i] = j+1;
                j++;
            }
            else if(j == 0){
                kmp[i] = 0;
            }
              
        }
        //System.out.println(Arrays.toString(kmp));
        int longest_pal_fromstart = kmp[n_len-1];
        return new StringBuilder(s.substring(longest_pal_fromstart)).reverse().toString() + s;
        
    }
}
