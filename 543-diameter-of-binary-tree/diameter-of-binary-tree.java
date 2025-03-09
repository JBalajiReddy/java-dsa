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
    public int diameterOfBinaryTree(TreeNode root) {
        int[] res = new int[1];
        res[0] = 0;
        findHeight(root, res);
        return res[0];
    }
    public int findHeight(TreeNode root, int[] res) {
        if(root == null) return 0;
        int leftH = findHeight(root.left, res);
        int rightH = findHeight(root.right, res);
        res[0] = Math.max(res[0], leftH + rightH);
        return (1 + Math.max(leftH, rightH));
    }
}
