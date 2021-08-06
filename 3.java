//https://leetcode.com/problems/longest-substring-without-repeating-characters/

/*Brute force: O(n^2) -> the solution can start at any index*/


/*Efficient O(n) -> Use an array to store index of a character in string 's'.
'left' is the start index of potential solution.
Case1: if already encountered the character before, check if it lies after 'left'. If it is before left, then we do count++.
Else, temp = map[character], Change left = temp. Calculate max. Store the new index of the character at map[character].
Case2: If new character, count++
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), res = 0,count = 0;
        int map[] = new int[128];
        Arrays.fill(map,-1);
        char s_arr[] = s.toCharArray();
        int left = -1;
        for(int i=0;i<n;i++){
            char cur = s_arr[i];
            if(map[cur]!=-1 && map[cur] > left){
                res = Math.max(res,count);
                left = map[cur];
                count = i-left;
            }else count++;
            map[cur]=i;
        }
        res = Math.max(res,count);
        return res;
    }
} //T.C - O(n), space - O(1)
