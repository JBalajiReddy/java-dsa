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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // build a map to store inorder value -> index for O(1) lookup
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        int[] preIdx = {0};
        return build(preorder, inorderMap, preIdx, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, Map<Integer, Integer> inorderMap, int[] preIdx, int start, int end) {
        if (start > end) return null;

        int rootVal = preorder[preIdx[0]++];
        TreeNode root = new TreeNode(rootVal);

        int rootIdx = inorderMap.get(rootVal);

        root.left = build(preorder, inorderMap, preIdx, start, rootIdx - 1);
        root.right = build(preorder, inorderMap, preIdx, rootIdx + 1, end);

        return root;
    }
}