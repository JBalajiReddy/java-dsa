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
    private int cnt = 0;
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null)
            return 0;
        dfs(root, targetSum);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return cnt;
    }

    private void dfs(TreeNode root, long target) {
        if (root == null)
            return;
        if (target == root.val)
            cnt++;
        dfs(root.left, target - root.val);
        dfs(root.right, target - root.val);
    }
}