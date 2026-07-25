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
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        if (res.size() == depth) {
            res.add(new ArrayList<>());
        }

        res.get(depth).add(node.val);
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}


// class Solution {
//     public List<List<Integer>> levelOrder(TreeNode root) {
//         List<List<Integer>> res = new ArrayList<>();
//         if (root == null) {
//             return res;
//         }

//         Queue<TreeNode> q = new LinkedList<>();
//         q.offer(root);

//         while (!q.isEmpty()) {
//             List<Integer> ls = new ArrayList<>();
//             int n = q.size();
//             for (int i = 0; i < n; i++) {
//                 TreeNode node = q.poll();
//                 if (node.left != null) {
//                     q.offer(node.left);
//                 }
//                 if (node.right != null) {
//                     q.offer(node.right);
//                 }
//                 ls.add(node.val);
//             }
//             res.add(ls);
//         }

//         return res;
//     }
// }

