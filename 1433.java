/*https://leetcode.com/problems/check-if-a-string-can-break-another-string/
GREEDY
Approach1 :
Sort the strings of length 'n'. Compare characters of strings at each position i.e. 0 to n-1.
Only one string has to have character >= character of another string at every positions

T.C nlogn, S.C O(n)
*/

class Solution {
    public boolean checkIfCanBreak(String s1, String s2) {
        char arr1[] = s1.toCharArray(), arr2[] = s2.toCharArray();
        Arrays.sort(arr1);Arrays.sort(arr2);

        boolean flag = false;
        int k = 0;
        
        while(k<s1.length() && arr1[k] == arr2[k++]);
       
        if(k-- < s1.length() && arr1[k] > arr2[k])flag=true;
        
        for(int i=k;i<s1.length();i++){
            if(flag && arr1[i] >= arr2[i])continue;
            if(!flag && arr1[i] <= arr2[i])continue;
            return false;
        }
        return true;
    }
}
/*
Approach2 :
Calculate characters count of the strings -> arr1, arr2 of size 26.
The first i where arr1[i] > arr2[i], it means that string1 has characters of smaller value and string2 has characters of larger value.
So, going forward, arr1[i] should always have cumulative count >= that of arr2

T.C - O(n), space - O(1)
*/

class Solution {
    public boolean checkIfCanBreak(String s1, String s2) {
        int n = s1.length(), arr1[] = new int[26], arr2[] = new int[26];
        
        for(int i=0;i<n;i++){
            arr1[s1.charAt(i) - 'a']++;
            arr2[s2.charAt(i) - 'a']++;
        }
        
        boolean flag = false;
        int count1 = 0, count2 = 0;
        int k = 0;
        
        while(k<n && count1 == count2){
            count1 += arr1[k];
            count2 += arr2[k];
            k++;
        }
        
        if(count1 < count2)flag = true;
        
        for(int i=k;i<26;i++){
            count1 += arr1[i];
            count2 += arr2[i];
            if(flag && count1 <= count2)continue;
            if(!flag && count1 >= count2)continue;
            return false;
        }
        return true;
        
    }
}
