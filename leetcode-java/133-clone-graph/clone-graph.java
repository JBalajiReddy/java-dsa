/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        // Map stores: Original Node -> Cloned Node
        Map<Node, Node> mp = new HashMap<>();
        return dfs(node, mp);
    }

    private Node dfs(Node node, Map<Node, Node> mp) {
        // Edge case: empty graph input
        if (node == null) {
            return null;
        }

        // Cycle/Duplicate Check: If already cloned, return the existing clone reference
        if (mp.containsKey(node)) {
            return mp.get(node);
        }

        // Step 1: Clone the value of current node
        Node copy = new Node(node.val);

        // Step 2: Register in map BEFORE recursive calls to handle cycle back-edges
        mp.put(node, copy);

        // Step 3: Recursively clone and link all neighbors
        for (Node neigh : node.neighbors) {
            copy.neighbors.add(dfs(neigh, mp));
        }

        return copy;
    }
}

class Solution_BFS {
    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        Map<Node, Node> oldToNew = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        oldToNew.put(node, new Node(node.val));
        q.add(node);

        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (Node nei : cur.neighbors) {
                if (!oldToNew.containsKey(nei)) {
                    oldToNew.put(nei, new Node(nei.val));
                    q.add(nei);
                }
                oldToNew.get(cur).neighbors.add(oldToNew.get(nei));
            }
        }
        return oldToNew.get(node);
    }
}