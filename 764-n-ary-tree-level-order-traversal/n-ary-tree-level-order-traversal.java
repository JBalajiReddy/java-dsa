/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            List<Integer> currLevel = new ArrayList<>();
            while (qSize > 0) {
                Node node = queue.poll();
                currLevel.add(node.val);
                for (Node child : node.children) {
                    if (child != null) {
                        queue.offer(child);
                    }
                }
                qSize--;
            }
            res.add(currLevel);
        }
        return res;
    }
}