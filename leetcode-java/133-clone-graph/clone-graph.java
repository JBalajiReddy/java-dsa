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
    // HashMap acts as our memory to prevent cycles and redundant work
    private HashMap<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        // Base case: Empty graph
        if (node == null) return null;

        // Base case: If we have already visited/cloned this node, return the clone
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // 1. Create the clone immediately
        Node cloneNode = new Node(node.val);
        
        // 2. Register it in the map BEFORE processing neighbors
        // This is crucial for handling cycles (e.g., A -> B -> A)
        map.put(node, cloneNode);

        // 3. Recursively clone all neighbors
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }
}

// class Solution {
//     public Node cloneGraph(Node node) {
//         if (node == null) return null;
//         HashMap<Node, Node> mp = new HashMap<>();
//         Queue<Node> q = new LinkedList<>();
//         q.offer(node);
//         mp.put(node, new Node(node.val));
//         while (!q.isEmpty()) {
//             Node curr = q.poll();
//             for (Node neigh : curr.neighbors) {
//                 if (!mp.containsKey(neigh)) {
//                     mp.put(neigh, new Node(neigh.val));
//                     q.offer(neigh);
//                 }
//                 mp.get(curr).neighbors.add(mp.get(neigh));
//             }
//         }
//         return mp.get(node);
//     }
// }