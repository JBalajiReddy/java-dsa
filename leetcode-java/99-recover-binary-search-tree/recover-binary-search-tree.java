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
    TreeNode prev;   // Tracks the previous node in the in-order traversal.
    TreeNode first;  // The first node in the out-of-order pair.
    TreeNode middle; // The second node when the pair is adjacent.
    TreeNode last;   // The second node when the pair is not adjacent.

    private void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        // A violation is found if the current node's value is less than the previous node's value.
        if (prev != null && (root.val < prev.val)) {
            // This is the first time a violation is detected.
            if (first == null) {
                // 'first' is the larger element that appears before the smaller one.
                first = prev;
                // 'middle' is the smaller element. This handles the case of adjacent swapped nodes.
                middle = root;
            } else {
                // If a second violation is found, it means the nodes were not adjacent.
                // 'last' is the second smaller element in the sequence.
                last = root;
            }
        }

        // Update the previous node to the current one for the next iteration.
        prev = root;
        inorder(root.right);
    }

    public void recoverTree(TreeNode root) {
        first = middle = last = null;
        // Initialize prev with a value smaller than any node to handle the first node correctly.
        prev = new TreeNode(Integer.MIN_VALUE);

        inorder(root);
        
        // This condition handles the case where two non-adjacent nodes were swapped.
        if (first != null && last != null) {
            int tmp = first.val;
            first.val = last.val;
            last.val = tmp;
        } 
        // This condition handles the case where two adjacent nodes were swapped.
        else if (first != null && middle != null) {
            int tmp = first.val;
            first.val = middle.val;
            middle.val = tmp;
        }
    }
}