/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;

        // Create a map to hold the original nodes and their copies
        Map<Node, Node> map = new HashMap<Node, Node>();

        // Loop 1: Copy all the nodes
        Node node = head;
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }

        // Loop 2: Assign next and random pointers
        node = head;
        while (node != null) {
            Node copiedNode = map.get(node);
            copiedNode.next = map.get(node.next);
            copiedNode.random = map.get(node.random);
            node = node.next;
        }

        // Return the head of the copied list
        return map.get(head);
    }
}
