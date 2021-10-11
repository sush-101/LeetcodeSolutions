//Approach 1
class Solution {
    int moves = 0;
    public int distributeCoins(TreeNode root) {
        getCoinsNodes(root);
        return moves;
    }
    private int[] getCoinsNodes(TreeNode root){
        //0->#nodes, 1->#coins
        int res[] = new int[2];
        if(root == null)return res;
        int left[] = getCoinsNodes(root.left);
        int right[] = getCoinsNodes(root.right);
        res[0] = 1+left[0]+right[0];
        res[1] = root.val + left[1] + right[1];
        moves += Math.abs(res[0]-res[1]);
        return res;
    }
}

//Approach2. DFS. 
// A node can give or takes coins = Math.abs(node.val-1)
//Start from leaf node. If value of left subtree is negative -> it needs coins, else it gives coins
//if from a subtree, we get -ve and from other subtree +ve, we do moves+= abs(+ve-ve+node.val-1)
class Solution {
    int moves = 0;
    public int distributeCoins(TreeNode root) {
        getCount(root.left);getCount(root.right);
        return moves;
    }
    private int getCount(TreeNode root){
        if(root == null)return 0;
        int left = getCount(root.left), right = getCount(root.right);
        int untilNow = root.val-1+left+right;
        moves += Math.abs(untilNow);
        return untilNow;
    }
}
//https://leetcode.com/problems/distribute-coins-in-binary-tree/
