//https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

//recursion
class Solution {
    public Node flatten(Node head) {
        helper(head);
        return head;
    }
    public Node[] helper(Node node){
        if(node == null)return new Node[]{null, null};
        Node temp = node, first = node, last = null;
        while(temp.next != null && temp.child == null){
            temp = temp.next;
        }
        if(temp.child != null){
            Node[] nextLevel = helper(temp.child);
            temp.child = null;
            Node tempNext = temp.next;
            temp.next = nextLevel[0];
            nextLevel[0].prev = temp;
            if(tempNext != null){
                tempNext.prev = nextLevel[1];
                nextLevel[1].next = tempNext;
                while(tempNext.next != null){
                    tempNext = tempNext.next;
                }
                last = tempNext;
            }else last = nextLevel[1];
        }else{
            last = temp;
        }
        return new Node[]{first, last};
    }
}

//using stack
class Solution {
    public Node flatten(Node head) {
        if(head == null)return null;
        Stack<Node> stack = new Stack<>();
        
        //'last' is the last node (which is not null) of res list
        Node last = head, temp = null;
        
        stack.push(last.next);
        if(last.child != null){
            stack.push(last.child);
            last.child = null;
        }
     
        while(!stack.isEmpty()){
            Node cur = stack.pop();
           
            while(cur != null){
                //adding 'cur' to end ('last') of the res list
                last.next = cur;cur.prev = last;
                last = cur; //or last = last.next
                
                if(cur.child != null){
                    //adding to backlog as this has to done after succeedinglevels are done
                    stack.push(cur.next);
                    //'cur' becomes its child
                    temp = cur.child;
                    cur.child = null;
                    cur = temp;
                }else{
                    //'cur' becomes its next
                    cur = cur.next;
                }
            }
            
        }
        return head;
    }
    
}

//t.c - O(n), space - O(n)
