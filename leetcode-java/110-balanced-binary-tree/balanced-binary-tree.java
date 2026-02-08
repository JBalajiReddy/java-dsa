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
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int lH = 0, rH = 0;
        if (root.left != null)
            lH = height(root.left);
        if (root.right != null)
            rH = height(root.right);
        return Math.abs(rH - lH) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode root) {
        if (root == null)
            return 0;
        int lHeight = 1 + height(root.left);
        int rHeight = 1 + height(root.right);
        return Math.max(lHeight, rHeight);
    }
}