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
    private Map<Long, Integer> mp;
    public int pathSum(TreeNode root, int targetSum) {
        mp = new HashMap<>();
        mp.put(0L, 1);
        return backtrack(root, 0L, targetSum);
    }

    private int backtrack(TreeNode node, long currSum, int target) {
        if (node == null)
            return 0;
        currSum += node.val;
        int cnt = mp.getOrDefault(currSum - target, 0);
        mp.put(currSum, mp.getOrDefault(currSum, 0) + 1);
        cnt += backtrack(node.left, currSum, target);
        cnt += backtrack(node.right, currSum, target);
        mp.put(currSum, mp.getOrDefault(currSum, 0) - 1);
        return cnt;
    }
}

// class Solution {
//     private int cnt = 0;
//     public int pathSum(TreeNode root, int targetSum) {
//         if (root == null)
//             return 0;
//         dfs(root, targetSum);
//         pathSum(root.left, targetSum);
//         pathSum(root.right, targetSum);
//         return cnt;
//     }

//     private void dfs(TreeNode root, long target) {
//         if (root == null)
//             return;
//         if (target == root.val)
//             cnt++;
//         dfs(root.left, target - root.val);
//         dfs(root.right, target - root.val);
//     }
// }