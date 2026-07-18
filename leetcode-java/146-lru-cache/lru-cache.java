class LRUCache {
    class Node {
        int key, val;
        Node prev, next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node head, tail;
    private int size;
    private Map<Integer, Node> mp;

    public LRUCache(int capacity) {
        size = capacity;
        mp = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!mp.containsKey(key)) {
            return -1;
        }
        Node node = mp.get(key);
        moveToFront(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (mp.containsKey(key)) {
            Node node = mp.get(key);
            node.val = value;
            moveToFront(node);
        } else {
            if (mp.size() == size) {
                Node lruNode = tail.prev;
                remove(lruNode);
                mp.remove(lruNode.key);
            }

            Node newNode = new Node(key, value);
            mp.put(key, newNode);
            InsertAtFront(newNode);

        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void InsertAtFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void moveToFront(Node node) {
        remove(node);
        InsertAtFront(node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */