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
        // Maps node value to the actual TreeNode object in memory
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        // Tracks all nodes that have an incoming edge (are children)
        Set<Integer> children = new HashSet<>();
        
        for (int[] d : descriptions) {
            int parentVal = d[0];
            int childVal = d[1];
            boolean isLeft = d[2] == 1;
            
            // Create parent and child if they haven't been encountered yet
            nodeMap.putIfAbsent(parentVal, new TreeNode(parentVal));
            nodeMap.putIfAbsent(childVal, new TreeNode(childVal));
            
            // Connect the child to the parent
            if (isLeft) {
                nodeMap.get(parentVal).left = nodeMap.get(childVal);
            } else {
                nodeMap.get(parentVal).right = nodeMap.get(childVal);
            }
            
            // Mark this value as a child (it cannot be the root)
            children.add(childVal);
        }
        
        // The root is the only node in the map that is NOT in the children set
        for (TreeNode node : nodeMap.values()) {
            if (!children.contains(node.val)) {
                return node;
            }
        }
        
        return null; // Fallback, should not be reached with valid input
    }
}