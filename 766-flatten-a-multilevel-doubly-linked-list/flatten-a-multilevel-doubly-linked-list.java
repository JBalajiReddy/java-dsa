/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return head;
        }

        Node current = head;
        while (current != null) {
            if (current.child != null) {
                Node nextNode = current.next;

                // Recursively flatten the child list
                current.next = flatten(current.child);
                current.next.prev = current;
                current.child = null;

                // Move to the end of the flattened child list
                while (current.next != null) {
                    current = current.next;
                }

                // Stitch the original next node
                if (nextNode != null) {
                    current.next = nextNode;
                    nextNode.prev = current;
                }
            }
            current = current.next;
        }
        return head;
    }
}
