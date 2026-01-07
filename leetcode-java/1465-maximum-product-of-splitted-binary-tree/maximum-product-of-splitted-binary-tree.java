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
    long sum = 0, max = Long.MIN_VALUE;
    public int maxProduct(TreeNode root) {
        int mod = 1000_000_007;
        sum = totalSum(root);
        dfs(root);
        return (int) (max % mod);
    }

    private long dfs(TreeNode root) {
        if (root == null)
            return 0;
        long left = dfs(root.left);
        long right = dfs(root.right);
        long currSum = left + right + root.val;
        max = Math.max(max, (sum - currSum) * currSum);
        return currSum;
    }

    private long totalSum(TreeNode root) {
        if (root == null)
            return 0;
        return totalSum(root.left) + totalSum(root.right) + root.val;
    }
}