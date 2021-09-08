//https://leetcode.com/problems/my-calendar-ii/

//Approach 1
/*
basic idea:
sort the events. If an event starts at a particular time, we increment the count.. if any event which has started before ends then we decrement the count.
if the count becomes > 2 at any instance, it means there are >2 events at that instance.

T.C: O(n*(logn+n)) => O(n^2 + n*logn) => O(n^2)
Space: O(n)
*/

class MyCalendarTwo {
    TreeMap<Integer,Integer> sorted; 
    
    public MyCalendarTwo() {
        sorted = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        sorted.put(start, sorted.getOrDefault(start,0)+1);
        sorted.put(end, sorted.getOrDefault(end,0)-1);
        
        int accumulated = 0;
        
        for(Map.Entry<Integer,Integer> entry:sorted.entrySet()){
            accumulated += entry.getValue();
            if(accumulated >= 3){
                sorted.put(start, sorted.get(start)-1);
                sorted.put(end, sorted.get(end)+1);
                return false;
            }
        }
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */

//Approach 2
/*
Segment tree. Tree gets updated and traversed in logn time.
T.C. O(n*logn) (if tree which forms is skewed then n^2)
Space: O(n)
*/

class MyCalendarTwo {
    Node root = null;
    public MyCalendarTwo() {
        
    }
    
    public boolean book(int start, int end) {
        int result = find(start, end, root);
        if(result == 2){
            return false;
        }
        Node temp = insert(start, end, root);
        if(root == null)root = temp;
        return true;
    }
    public int find(int start, int end, Node node){
        if(node == null)return 0;
        if(start >= node.end)return find(start, end, node.right);
        if(end <= node.start)return find(start, end, node.left);
        if(start >= node.start && end < node.end)return node.count;
        return Math.max(node.count, Math.max(find(start, end, node.left), find(start, end, node.right)));
    }
    
    public Node insert(int start, int end, Node node){
        if(node == null)return new Node(start, end, 1);
        if(start >= node.end)node.right = insert(start, end, node.right);
        else if(end <= node.start)node.left = insert(start, end, node.left);
        else{
            int left_inter = Math.max(start, node.start);
            int right_inter = Math.min(end, node.end);
            int leftmost = Math.min(start, node.start);
            int rightmost = Math.max(end, node.end);
            node.start = left_inter;node.end = right_inter;
            node.count += 1;
            node.right = insert(right_inter, rightmost, node.right);
            node.left = insert(leftmost, left_inter, node.left);
        }
        return node;
        
    }
}
class Node{
    Node left, right;
    int count, start, end;
    Node(int start, int end, int count){
        this.start = start;
        this.end = end;
        this.count = count;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */

/*
["MyCalendarTwo","book","book","book","book","book"]
[[],[10,20],[10,20],[15,22],[20,22],[20,22]]
*/
