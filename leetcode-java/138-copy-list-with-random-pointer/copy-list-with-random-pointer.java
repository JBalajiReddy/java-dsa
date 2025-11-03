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
        Map<Node, Node> map = new HashMap<>();
        Node node = head;

        //create copies
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }

        //assign next, random pointers
        node = head;
        while (node != null) {
            Node cpNode = map.get(node);
            cpNode.next = map.get(node.next);
            cpNode.random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }
}