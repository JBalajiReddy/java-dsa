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
    static int num = 0, count;
    public int kthSmallest(TreeNode root, int k) {
        count = k;
        inOrder(root);
        return num;
    }

    private void inOrder(TreeNode root) {
        if (root == null)
            return;

        inOrder(root.left);

        count--;
        if (count == 0) {
            num = root.val;
            return;
        }

        inOrder(root.right);
    }
}