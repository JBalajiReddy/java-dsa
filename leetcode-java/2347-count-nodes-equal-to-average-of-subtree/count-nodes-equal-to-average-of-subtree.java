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
    private int matchingSubtreeCount = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return matchingSubtreeCount;
    }

    private int[] dfs(TreeNode node) {
        // Base case: empty node contributes 0 to sum and 0 to count
        if (node == null) {
            return new int[] { 0, 0 };
        }

        // Post-order traversal: collect subproblem data first
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // Aggregate current node's subtree statistics
        int totalSum = left[0] + right[0] + node.val;
        int totalCount = left[1] + right[1] + 1;

        // Verify problem criteria using floor division
        if (totalSum / totalCount == node.val) {
            matchingSubtreeCount++;
        }

        // Return aggregated state to parent caller
        return new int[] { totalSum, totalCount };
    }
}