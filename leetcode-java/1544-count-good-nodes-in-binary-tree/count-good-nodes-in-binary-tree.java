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
    class Pair {
        TreeNode node;
        int max;
        Pair(TreeNode node, int max) {
            this.node = node;
            this.max = max;
        }
    }

    public int goodNodes(TreeNode root) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, root.val));
        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair p = q.poll();
                TreeNode node = p.node;
                int max = p.max;

                if (node.val >= max) cnt++;
                max = Math.max(max, node.val);

                if (node.left != null) {
                    q.offer(new Pair(node.left, max));
                }

                if (node.right != null) {
                    q.offer(new Pair(node.right, max));
                }
            }
        }
        return cnt;
    }
}