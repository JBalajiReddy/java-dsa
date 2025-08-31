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
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        Set<Integer> seen = new HashSet<>();
        return find(root, seen, k);
    }

    private boolean find(TreeNode root, Set<Integer> set, int k) {
        if (root == null) return false;

        int complement = k - root.val;
        if (set.contains(complement)) return true;

        set.add(root.val);

        return find(root.left, set, k) || find(root.right, set, k);
    }
}