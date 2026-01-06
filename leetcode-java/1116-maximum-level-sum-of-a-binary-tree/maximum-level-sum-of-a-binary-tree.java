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
    public int maxLevelSum(TreeNode root) {
        if (root == null)
            return 0;
        int maxSum = Integer.MIN_VALUE;
        int maxLvl = 1;
        int currentLevel = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            int currentSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                currentSum += node.val;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxLvl = currentLevel;
            }
            currentLevel++;
        }
        return maxLvl;
    }
}