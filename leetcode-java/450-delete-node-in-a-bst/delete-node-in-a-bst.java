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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }

        if (root.val == key) {
            return helper(root);
        }

        TreeNode curr = root;

        //traverse and find parent of key
        while (curr != null) {
            if (curr.val > key) {
                if ((curr.left != null) && (curr.left.val == key)) {
                    curr.left = helper(curr.left);
                    break;
                } else {
                    curr = curr.left;
                }
            } else {
                if ((curr.right != null) && (curr.right.val == key)) {
                    curr.right = helper(curr.right);
                    break;
                } else {
                    curr = curr.right;
                }
            }
        }

        return root;
    }

    /**
     * Removes targetNode and re-attaches its left and right subtrees 
     * while preserving the BST property.
     */
    private TreeNode helper(TreeNode node) { //targetNode
        if (node.left == null) {
            return node.right;
        } 
        
        if (node.right == null) {
            return node.left;
        }

        //targetNode has both left and right children
        TreeNode rightSubtree = node.right;
        TreeNode maxNodeInLeftSubtree = findMaxNode(node.left);

        // Attach targetNode's entire right subtree to the right of the largest node in left subtree
        maxNodeInLeftSubtree.right = rightSubtree;

        // Finds the node with the maximum value in a subtree (the rightmost node).
        return node.left;
    }

    private TreeNode findMaxNode(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
}