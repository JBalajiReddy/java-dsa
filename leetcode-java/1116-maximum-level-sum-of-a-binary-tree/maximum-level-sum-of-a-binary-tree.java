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

class Pair {
    TreeNode node;
    int lvl;
    Pair(TreeNode node, int lvl) {
        this.node = node;
        this.lvl = lvl;
    }
}

class Solution {
    public int maxLevelSum(TreeNode root) {
        int maxSum = root.val, lvl = 1;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 1));
        while (!q.isEmpty()) {
            int size = q.size();
            int sum = 0, currLvl = 0;
            for (int i = 0; i < size; i++) {
                Pair p = q.poll();
                currLvl = p.lvl;
                sum += p.node.val;
                if (p.node.left != null) {
                    q.offer(new Pair(p.node.left, p.lvl + 1));
                } if (p.node.right != null) {
                    q.offer(new Pair(p.node.right, p.lvl + 1));
                }
            }
            if (sum > maxSum) {
                maxSum = sum;
                lvl = currLvl;
            }
        }
        return lvl;
    }
}