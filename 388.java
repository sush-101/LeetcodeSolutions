//https://leetcode.com/problems/longest-absolute-file-path/

/*Approach1
When there is a new line, it can  be over the existing pile (/) or (one or more) steps backward (. . . ../../)
This can be found out using #\t
the existing pile (if present) can be fetched in O(1) using an array or stack because only the recent one has to be stored.
Here in approach1, stack is used, in approach 2 array is used.
t.c.-O(n)
s.c-O(n)
*/

class Solution {
    public int lengthLongestPath(String input) {
        String arr[] = input.split("\n");
        int n = arr.length, map[] = new int[n], max = 0;
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0;i<n;i++){
            int count = 0;
            String cur = arr[i];
            while(cur.charAt(count) == '\t'){
                count++; 
            }
            cur = cur.substring(count);
            while(stack.size()>count){
                stack.pop();
            }
            
            int temp = cur.length() + (stack.isEmpty()?0:map[stack.peek()]);
            if(cur.contains("."))
                max = Math.max(max, temp);
            map[i] = temp+1;
            stack.push(i);
        }
        
        return max;
    }
}


/*Approach2*/
class Solution {
    public int lengthLongestPath(String input) {
        //all the steps
        String arr[] = input.split("\n");
        //map has recent length
        int n = arr.length, map[] = new int[n+1], max = 0;
        
        for(int i=0;i<n;i++){
            String cur = arr[i];
            int count = cur.lastIndexOf("\t")+1; //adding +1 to prevent map[-1] case
            //cur file length+prev path length
            int temp = cur.length()-count + (map[count]);
            if(cur.contains(".")) //update answer only if it is a file
                max = Math.max(max, temp);
            map[count+1] = temp+1; //temp+'1' because of '/'
        }
        
        return max;
    }
}
