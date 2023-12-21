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
    List<TreeNode> res = new ArrayList<>();
    Set<Integer> set = null;
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        set = new HashSet();
        for(int node: to_delete){
            set.add(node);
        }
        helper(root, true);
        return res;
    }
    public TreeNode helper(TreeNode root, boolean prev){
        if(root == null)return null;
        boolean to_be_deleted = set.contains(root.val);
        if(prev && !to_be_deleted)res.add(root);
        root.left = helper(root.left, to_be_deleted);
        root.right = helper(root.right, to_be_deleted);
        return to_be_deleted ? null : root;
    }
}