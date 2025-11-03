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
    boolean res;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        res = false;
        dfs(root, 0, targetSum);
        return res;
    }

    private void dfs(TreeNode root, int sum, int target) {
        if (root == null)
            return;
        sum += root.val;
        if (root.left == null && root.right == null && sum == target) {
            res = true;
            return;
        }
        dfs(root.left, sum, target);
        dfs(root.right, sum, target);
    }
}