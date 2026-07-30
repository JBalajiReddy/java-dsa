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
    public int maxPathSum(TreeNode root) {
        int[] sum = { Integer.MIN_VALUE };
        dfs(root, sum);
        return sum[0];
    }

    private int dfs(TreeNode root, int[] sum) {
        if (root == null) {
            return 0;
        }

        // Recursively compute the max gain from left and right subtrees.
        // Math.max(0, ...) ensures we ignore subtrees that yield a negative sum,
        // as adding them would reduce the overall path total.
        int lMax = Math.max(0, dfs(root.left, sum));
        int rMax = Math.max(0, dfs(root.right, sum));

        // Update the global max path sum considering 'root' as the apex node 
        // of an inverted 'V' path (combining left branch + root + right branch).
        sum[0] = Math.max(sum[0], lMax + root.val + rMax);

        // Return the maximum single-branch path sum extending upward to the parent.
        // A path cannot split to both children and continue upward, 
        // so we must choose the larger of the two child branches.
        return root.val + Math.max(lMax, rMax);
    }
}