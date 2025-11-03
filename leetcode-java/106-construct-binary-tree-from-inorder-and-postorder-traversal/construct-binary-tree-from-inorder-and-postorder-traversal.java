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
    int postLen;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postLen = postorder.length - 1;
        return build(inorder, postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] inorder, int[] postorder, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;
        int rootVal = postorder[postLen--];
        TreeNode root = new TreeNode(rootVal);
        int rootIdx = inStart;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                rootIdx = i;
                break;
            }
        }
        root.right = build(inorder, postorder, rootIdx + 1, inEnd);
        root.left = build(inorder, postorder, inStart, rootIdx - 1);
        return root;
    }
}