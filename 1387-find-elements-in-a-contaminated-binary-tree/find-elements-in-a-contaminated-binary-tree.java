/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class FindElements {

    Set<Integer> set;

    public void dfs(TreeNode currNode, int currVal) {
        if (currNode == null)
            return;
        set.add(currVal);
        dfs(currNode.left, currVal * 2 + 1);
        dfs(currNode.right, currVal * 2 + 2);
    }

    public FindElements(TreeNode root) {
        set = new HashSet<>();
        dfs(root, 0);
    }

    public boolean find(int target) {
        return set.contains(target);
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */