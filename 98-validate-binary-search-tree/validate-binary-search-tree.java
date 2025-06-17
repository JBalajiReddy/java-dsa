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
    ArrayList<Integer> ls;

    public boolean isValidBST(TreeNode root) {
        ls = new ArrayList<>();
        inOrder(root);
        return isSorted(ls);
    }

    private void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        ls.add(root.val);
        inOrder(root.right);
    }

    private boolean isSorted(ArrayList<Integer> ls) {
        for (int i = 0; i < ls.size() - 1; i++) {
            if (ls.get(i) >= ls.get(i + 1))
                return false;
        }
        return true;
    }
}