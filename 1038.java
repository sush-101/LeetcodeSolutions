//https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode bstToGst(TreeNode root) {
        replaceAndGet(root, 0);
        return root;
    }
    private int replaceAndGet(TreeNode root, int toAdd){
        if(root == null)return 0;
        int cur = root.val, res = 0;

        if(root.right!=null){
            int rightVal = replaceAndGet(root.right, toAdd);
            cur += rightVal + root.right.val;
        }else cur += toAdd;
        
        if(root.left != null){
            int leftVal = replaceAndGet(root.left, cur);
            res = leftVal + root.left.val - cur;
        }
      
        root.val = cur;  
        return res;
    }
}
/*
[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
[6,2,9,0,4,7,11,null,1,3,5,null,8,10,12]

Take root.right.val, add to the root.val . Take res from root.right and add to root.val .
res indicates sum of all nodes values in a left subtree. Sum of all nodes of right subtree is updated in the root.val.
DFS
T.C - O(n)
S.C - O(n)
where n is #nodes
*/
