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
    int min;
    Integer prev;

    public int getMinimumDifference(TreeNode root) {
        min = Integer.MAX_VALUE;
        prev = null;
        return inOrder(root);
    }

    private int inOrder(TreeNode root) {
        if (root == null)
            return min;

        inOrder(root.left);

        if (prev != null)
            min = Math.min(min, root.val - prev);

        prev = root.val;

        inOrder(root.right);
        return min;
    }
}