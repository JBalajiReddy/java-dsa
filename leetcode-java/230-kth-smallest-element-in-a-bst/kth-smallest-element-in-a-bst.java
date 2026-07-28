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
    private int num, cnt;

    public int kthSmallest(TreeNode root, int k) {
        cnt = k;
        dfs(root);
        return num;
    }

    private void dfs(TreeNode root) {
        if (root == null || cnt == 0) { // Stop early if answer is already found
            return;
        }

        dfs(root.left);

        if (cnt == 0)
            return; // Prevent unnecessary work

        cnt--;
        if (cnt == 0) {
            num = root.val;
            return;
        }

        dfs(root.right);
    }
}