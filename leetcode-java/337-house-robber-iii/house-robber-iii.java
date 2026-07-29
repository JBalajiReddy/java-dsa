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
    public int rob(TreeNode root) {
        int[] result = helper(root);
        return Math.max(result[0], result[1]);
    }

    // returns [maxIfRobbed, maxIfNotRobbed]
    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[] { 0, 0 };
        }

        int[] left = helper(root.left);
        int[] right = helper(root.right);

        // If we rob root, we CANNOT rob left or right children
        int robCurrent = root.val + left[1] + right[1];

        // If we don't rob root, we can choose to rob or skip left/right children
        int skipCurrent = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[] { robCurrent, skipCurrent };
    }
}

// public class Solution {
//     private Map<TreeNode, Integer> cache;

//     public int rob(TreeNode root) {
//         cache = new HashMap<>();
//         cache.put(null, 0);
//         return dfs(root);
//     }

//     private int dfs(TreeNode root) {
//         if (cache.containsKey(root)) {
//             return cache.get(root);
//         }

//         int res = root.val;

//         if (root.left != null) {
//             res += dfs(root.left.left) + dfs(root.left.right);
//         }
//         if (root.right != null) {
//             res += dfs(root.right.left) + dfs(root.right.right);
//         }

//         res = Math.max(res, dfs(root.left) + dfs(root.right));
//         cache.put(root, res);
//         return res;
//     }
// }