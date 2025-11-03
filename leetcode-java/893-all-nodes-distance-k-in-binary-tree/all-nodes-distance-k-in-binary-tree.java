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
        // Use a queue for a Breadth-First Search (BFS) to traverse the tree
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            // If a left child exists, store its parent and add it to the queue
            if (curr.left != null) {
                mp.put(curr.left, curr);
                q.offer(curr.left);
            }
            // If a right child exists, store its parent and add it to the queue
            if (curr.right != null) {
                mp.put(curr.right, curr);
                q.offer(curr.right);
            }
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // Map to store the parent of each node, effectively creating a way to traverse upwards
        Map<TreeNode, TreeNode> parentTrack = new HashMap<>(); 
        markParents(root, parentTrack);

        // Map to keep track of visited nodes to prevent cycles during the BFS
        Map<TreeNode, Boolean> visited = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        
        // Start the BFS from the target node
        q.offer(target);
        visited.put(target, true);

        // Counter for the current distance level from the target node
        int curr_size = 0;
        
        while (!q.isEmpty()) {
            int n = q.size();
            
            // If the current level is k, we have found all nodes at the desired distance
            if (curr_size == k)
                break;
            
            // Move to the next distance level
            curr_size++;

            for (int i = 0; i < n; i++) {
                TreeNode curr = q.poll();

                // Explore neighbors: left child, right child, and parent
                
                // Check left child
                if (curr.left != null && visited.get(curr.left) == null) {
                    q.offer(curr.left);
                    visited.put(curr.left, true);
                }
                // Check right child
                if (curr.right != null && visited.get(curr.right) == null) {
                    q.offer(curr.right);
                    visited.put(curr.right, true);
                }
                // Check parent using the parentTrack map
                if (parentTrack.get(curr) != null && visited.get(parentTrack.get(curr)) == null) {
                    q.offer(parentTrack.get(curr));
                    visited.put(parentTrack.get(curr), true);
                }
            }
        }

        // Collect all nodes currently in the queue, which are at distance k
        List<Integer> ls = new ArrayList<>();
        while (!q.isEmpty())
            ls.add(q.poll().val);

        return ls;
    }
}