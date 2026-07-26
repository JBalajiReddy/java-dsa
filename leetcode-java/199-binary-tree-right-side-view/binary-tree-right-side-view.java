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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode root, int depth, List<Integer> ls) {
        if (root == null) {
            return;
        }

        if (depth == ls.size()) {
            ls.add(root.val);
        }

        dfs(root.right, depth + 1, ls);
        dfs(root.left, depth + 1, ls);
    }
}


// class Solution {
//     public List<Integer> rightSideView(TreeNode root) {
//         List<Integer> res = new ArrayList<>();
//         if (root == null) {
//             return res;
//         }
//         bfs(root, res);
//         return res;
//     }

//     private void bfs(TreeNode root, List<Integer> ls) {
//         Queue<TreeNode> q = new LinkedList<>();
//         q.offer(root);

//         while (!q.isEmpty()) {
//             int n = q.size();
//             for (int i = 0; i < n; i++) {
//                 TreeNode node = q.poll();
//                 if (i == n - 1) {
//                     ls.add(node.val);
//                 }

//                 if (node.left != null) {
//                     q.offer(node.left);
//                 }

//                 if (node.right != null) {
//                     q.offer(node.right);
//                 }
//             }
//         }
//     }
// }