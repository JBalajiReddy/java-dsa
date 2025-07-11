class LRUCache {
    class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private int capacity;
    private Map<Integer, Node> cache;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();

        // Dummy head and tail nodes to simplify adding/removing
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key))
            return -1;

        Node node = cache.get(key);
        moveToFront(node); // Recently used
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // Update value and move to front
            Node node = cache.get(key);
            node.value = value;
            moveToFront(node);
        } else {
            if (cache.size() == capacity) {
                // Remove least recently used from list and map
                Node lru = tail.prev;
                remove(lru);
                cache.remove(lru.key);
            }

            // Add new node to front
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            insertToFront(newNode);
        }
    }

    // Helper: remove a node from the list
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Helper: insert a node right after head (most recently used)
    private void insertToFront(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    // Helper: move a node to the front
    private void moveToFront(Node node) {
        remove(node);
        insertToFront(node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */