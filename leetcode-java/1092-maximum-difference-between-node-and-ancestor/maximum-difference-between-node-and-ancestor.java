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
    public int maxAncestorDiff(TreeNode root) {
        // Pass the root node's value as both initial max and min along the path
        return dfs(root, root.val, root.val);
    }

    private int dfs(TreeNode root, int max, int min) {
        // Base case: Reached past a leaf node.
        // The maximum ancestor difference for this root-to-leaf path is (max - min).
        if (root == null) {
            return max - min;
        }

        // Update the maximum and minimum values encountered on the current path
        max = Math.max(root.val, max);
        min = Math.min(root.val, min);

        // Recursively compute the max difference in the left and right subtrees
        int left = dfs(root.left, max, min);
        int right = dfs(root.right, max, min);

        // Return the larger maximum difference found between the two branches
        return Math.max(left, right);
    }
}