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
    public int maxDepth(TreeNode root) {
        int[] maxH = { 0 };
        return dfs(root, maxH); 
    }

    private int dfs(TreeNode root, int[] h) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left, h);
        int right = dfs(root.right, h);

        return h[0] = (1 + Math.max(left, right));
    }
}