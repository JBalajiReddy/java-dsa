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
    private void inorderTraversal(TreeNode node, List<Integer> ls) {
        if (node == null)
            return;
        inorderTraversal(node.left, ls);
        ls.add(node.val);
        inorderTraversal(node.right, ls);
    }

    private TreeNode createBST(List<Integer> ls, int start, int end) {
        if (start > end)
            return null;
        int mid = (start + end) / 2;
        TreeNode leftSubTree = createBST(ls, start, mid - 1);
        TreeNode rightSubTree = createBST(ls, mid + 1, end);
        TreeNode node = new TreeNode(
                ls.get(mid),
                leftSubTree,
                rightSubTree);
        return node;
    }

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);
        return createBST(inorder, 0, inorder.size() - 1);
    }
}