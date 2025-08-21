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

//A balanced binary tree, also referred to as a height-balanced binary tree, is defined as a binary tree in which the height of the left and right subtree of any node differ by not more than 1.
class Solution {
    public boolean isBalanced(TreeNode root) {

        return height(root) == -1 ? false : true;
    }

    private int height(TreeNode root) {
        if (root == null) return 0;

        int lH = height(root.left);
        if (lH == -1) return -1;

        int rH = height(root.right);
        if (rH == -1) return -1;

        if (Math.abs(lH - rH) > 1) return -1;
        
        return 1 + Math.max(lH, rH);
    }
}