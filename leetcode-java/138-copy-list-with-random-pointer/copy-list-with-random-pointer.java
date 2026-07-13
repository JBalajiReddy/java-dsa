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
        HashMap<Node, Node> mp = new HashMap<>();
        mp.put(null, null);

        Node cur = head;
        while (cur != null) {
            if (!mp.containsKey(cur)) {
                mp.put(cur, new Node(0));
            }
            mp.get(cur).val = cur.val;

            if (!mp.containsKey(cur.next)) {
                mp.put(cur.next, new Node(0));
            }
            mp.get(cur).next = mp.get(cur.next);

            if (!mp.containsKey(cur.random)) {
                mp.put(cur.random, new Node(0));
            }
            mp.get(cur).random = mp.get(cur.random);
            cur = cur.next;
        }
        return mp.get(head);
    }
}