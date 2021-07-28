//https://leetcode.com/problems/reverse-nodes-in-k-group/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// recursion based solution
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 1 || !checkIfK(head,k))return head;
        ListNode prev = null, cur = head, nxt = null;
        int temp = k;
        while(temp-- != 0){
            nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        // reverseKGroup for nodes after current group of K
        head.next = reverseKGroup(cur,k);
        return prev;    
    }
    //check if  #nodes to be reversed >= k
    public boolean checkIfK(ListNode node, int k){
        while(k!=0 && node!=null){
            node = node.next;  
            k--;
        }
        return k<=0;
    }
}

// T.C = O(n), space = O(1)
