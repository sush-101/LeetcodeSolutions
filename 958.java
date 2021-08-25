//https://leetcode.com/problems/check-completeness-of-a-binary-tree/

//Approach 1: bfs. when you encounter null, the elements present in the queue shouldn't have any children - T.c = o(n), space-O(n)

//Approach 2: dfs. 
/*
count elements in left subtree and right subtree.
not a complete bt:
1. if left or right subtree is not a complete bt
2. #nodes in left subtree < #nodes on right
3. if left subtree is not a perfect binary tree but a complete binary tree, right binary tree should have #2^(height of left)-1


T.C - O(n), space - O(n)
*/

class Solution {
    public boolean isCompleteTree(TreeNode root) {
        int pow[] = new int[10],prod = 2;
 
        for(int i=0;i<10;i++,prod*=2){
            pow[i] = prod-1;
        }
        
        int res[] = isComplete(root, pow);
        return res[0]!=-2;   
    }
    
    int[] isComplete(TreeNode root, int[] pow){ 
        if(root == null){ 
            return new int[]{-1,0};
        }
        
        int ans[] = new int[2];
        int[] left = isComplete(root.left, pow);
        int[] right = isComplete(root.right, pow);
        
        //if any of the side violates
        //#nodes on left should be > #nodes on right
        if(left[0] == -2 || right[0] == -2  || left[1] < right[1]){
            ans[0]=-2;
            return ans;
        }
        
        if(left[0]>0 && left[1] < pow[left[0]] && right[1] != pow[left[0]-1]){
            ans[0]=-2;
            return ans;
        }
        
        ans[1] = left[1]+right[1]+1;
        ans[0] = left[0]+1;
        
        return ans;
    }
}
