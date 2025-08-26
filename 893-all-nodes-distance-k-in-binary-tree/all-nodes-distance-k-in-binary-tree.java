/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    private void markParents(TreeNode root, Map<TreeNode, TreeNode> mp) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (curr.left != null) {
                mp.put(curr.left, curr);
                q.offer(curr.left);
            }
            if (curr.right != null) {
                mp.put(curr.right, curr);
                q.offer(curr.right);
            }
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parentTrack = new HashMap<>(); //child -> parent
        markParents(root, parentTrack);

        Map<TreeNode, Boolean> visited = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(target);
        visited.put(target, true);

        int curr_size = 0;
        while (!q.isEmpty()) {
            int n = q.size();
            if (curr_size == k)
                break;
            curr_size++;

            for (int i = 0; i < n; i++) {
                TreeNode curr = q.poll();

                if (curr.left != null && visited.get(curr.left) == null) {
                    q.offer(curr.left);
                    visited.put(curr.left, true);
                }
                if (curr.right != null && visited.get(curr.right) == null) {
                    q.offer(curr.right);
                    visited.put(curr.right, true);
                }
                if (parentTrack.get(curr) != null && visited.get(parentTrack.get(curr)) == null) {
                    q.offer(parentTrack.get(curr));
                    visited.put(parentTrack.get(curr), true);
                }
            }
        }

        List<Integer> ls = new ArrayList<>();
        while (!q.isEmpty())
            ls.add(q.poll().val);

        return ls;
    }
}