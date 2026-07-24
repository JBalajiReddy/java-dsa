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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;

        while (curr != null) {
            // Case 1: Both p and q are strictly smaller than the current node.
            // This means the split point (LCA) lies further down in the left subtree.
            if ((curr.val > p.val) && (curr.val > q.val)) {
                curr = curr.left;
            } 
            // Case 2: Both p and q are strictly larger than the current node.
            // This means the split point (LCA) lies further down in the right subtree.
            else if ((p.val > curr.val) && (q.val > curr.val)) {
                curr = curr.right;
            } 
            // Case 3: Split point found!
            // Either p and q are on opposite sides of curr, or curr is equal to p or q.
            // In all these scenarios, curr is the Lowest Common Ancestor.
            else {
                return curr;
            }
        }

        // Return null if the targets are not present in the tree
        return null;
    }
}