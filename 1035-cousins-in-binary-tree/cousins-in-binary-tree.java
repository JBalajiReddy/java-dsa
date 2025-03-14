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

// same level - different parent
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null)
            return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int childCount = 0;
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                int sameParentCount = 0;
                TreeNode node = queue.poll();
                if (node.left != null) {
                    if (node.left.val == x || node.left.val == y) {
                        childCount++;
                        sameParentCount++;
                    }
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    if (node.right.val == x || node.right.val == y) {
                        childCount++;
                        sameParentCount++;
                    }
                    queue.offer(node.right);
                }

                if (sameParentCount == 2)
                    return false;
            }
            if (childCount == 2)
                return true;
            if (childCount == 1)
                return false;
        }
        return false;
    }
}