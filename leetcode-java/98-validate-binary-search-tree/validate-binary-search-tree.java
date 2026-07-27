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
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(TreeNode root, long min, long max) {
        // Base case: An empty tree or leaf child is a valid BST
        if (root == null) {
            return true;
        }

        // The current node must be strictly within (min, max) ( <= reject duplicates >= )
        if (root.val <= min || root.val >= max) {
            return false;
        }

        // Left child: upper bound becomes current node's value
        // Right child: lower bound becomes current node's value
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }
}

/**

For a left child, the upper bound determines validity because its value can extend down to $-\infty$, but it can never exceed its parent.

For a right child, the lower bound determines validity because its value can extend up to $+\infty$, but it can never drop below its parent.

*/