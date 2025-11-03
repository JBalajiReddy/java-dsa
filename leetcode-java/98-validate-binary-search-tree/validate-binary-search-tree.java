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
    public boolean isValidBST(TreeNode root) {
        return isValid(Long.MIN_VALUE, root, Long.MAX_VALUE);
    }

    private boolean isValid(long min, TreeNode root, long max) {
        if (root == null)
            return true;
        if (root.val >= max || root.val <= min)
            return false;
        return isValid(min, root.left, root.val) && isValid(root.val, root.right, max);
    }
}