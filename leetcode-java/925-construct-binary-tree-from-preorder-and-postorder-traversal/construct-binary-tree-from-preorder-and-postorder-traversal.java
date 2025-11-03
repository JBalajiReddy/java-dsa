/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

class Solution {
    private int postIdx = 0;
    private int preIdx = 0;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return recur(preorder, postorder);
    }

    public TreeNode recur(int[] preorder, int[] postorder) {
        // process nodes in preorder to determine which nodes to create
        TreeNode root = new TreeNode(preorder[preIdx++]);

        // postorder to recognize when a subtree is complete
        if (root.val != postorder[postIdx]) {
            root.left = recur(preorder, postorder);
        }
        if (root.val != postorder[postIdx]) {
            root.right = recur(preorder, postorder);
        }

        postIdx++;
        return root;

    }
}