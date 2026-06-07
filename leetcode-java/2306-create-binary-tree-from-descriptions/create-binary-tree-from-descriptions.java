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
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> mp = new HashMap<>();
        Set<Integer> children = new HashSet<>();
        for (int[] d : descriptions) {
            int parentVal = d[0];
            int childVal = d[1];
            boolean isLeft = d[2] == 1;
            if (!mp.containsKey(parentVal)) {
                mp.put(parentVal, new TreeNode(parentVal));
            }

            if (!mp.containsKey(childVal)) {
                mp.put(childVal, new TreeNode(childVal));
            }

            if (isLeft) {
                mp.get(parentVal).left = mp.get(childVal);
            } else {
                mp.get(parentVal).right = mp.get(childVal);
            }

            children.add(childVal);
        }

        for (TreeNode node : mp.values()) {
            if (!children.contains(node.val)) {
                return node;
            }
        }

        return null;
    }
}