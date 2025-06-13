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

    int preIdx = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, inorder.length - 1);
    }

    public TreeNode build(int[] preorder, int[] inorder, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;

        int rootVal = preorder[preIdx++];
        TreeNode root = new TreeNode(rootVal);
        int rootIdx = inStart;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                rootIdx = i;
                break;
            }
        }
        root.left = build(preorder, inorder, inStart, rootIdx - 1);
        root.right = build(preorder, inorder, rootIdx + 1, inEnd);
        return root;
    }
}