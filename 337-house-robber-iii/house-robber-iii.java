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
    public int rob(TreeNode root) {
        int max[] = recurse(root);
        return Math.max(max[0], max[1]);
    }
    public int[] recurse(TreeNode root){
        //leave take
        int res[] = new int[2];
        if(root == null)return res;
        int max1[] = recurse(root.left);
        int max2[] = recurse(root.right);
        res[0] = Math.max(max1[0], max1[1]);
        res[0] += Math.max(max2[0], max2[1]);
        res[1] = root.val + max1[0] + max2[0];
        return res;
    }
}