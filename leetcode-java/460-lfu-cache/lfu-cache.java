class LFUCache {
    // Represents your vector{key, value, freq} with doubly linked properties
    private static class Node {
        int key, value, freq;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1; // Default frequency on insertion
        }
    }

    // Represents the std::list container to maintain LRU order within a frequency
    private static class DoublyLinkedList {
        Node head, tail;
        int size;

        DoublyLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            this.size = 0;
        }

        // Equivalent to push_front()
        void pushFront(Node node) {
            Node nextNode = head.next;
            head.next = node;
            node.prev = head;
            node.next = nextNode;
            nextNode.prev = node;
            size++;
        }

        // Equivalent to erase() via iterator address
        void erase(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            size--;
        }

        // Equivalent to pop_back()
        Node popBack() {
            if (size == 0) return null;
            Node lastNode = tail.prev;
            erase(lastNode);
            return lastNode;
        }
    }

    private final int cap;
    private int currentSize;
    private int minFreq; // Replaces freq.begin()->first by tracking the absolute lowest frequency
    private final Map<Integer, Node> mp; // key -> Node reference mapping
    private final Map<Integer, DoublyLinkedList> freq; // freq -> DoublyLinkedList mapping

    public LFUCache(int capacity) {
        this.cap = capacity;
        this.currentSize = 0;
        this.minFreq = 0;
        this.mp = new HashMap<>();
        this.freq = new HashMap<>();
    }
    
    // Equivalent to your makeMostFrequentlyUsed(int key) logic
    private void makeMostFrequentlyUsed(Node node) {
        int f = node.freq;
        DoublyLinkedList currentList = freq.get(f);
        currentList.erase(node);

        // If the current lowest frequency list is empty, increment the tracking pointer
        if (f == minFreq && currentList.size == 0) {
            minFreq++;
        }

        node.freq++;
        // Push front into the upgraded frequency group list
        freq.computeIfAbsent(node.freq, k -> new DoublyLinkedList()).pushFront(node);
    }
    
    public int get(int key) {
        if (!mp.containsKey(key)) {
            return -1;
        }
        
        Node node = mp.get(key);
        int value = node.value;
        makeMostFrequentlyUsed(node);
        
        return value;
    }
    
    public void put(int key, int value) {
        if (cap == 0) return;

        // Condition 1: Key already exists, update value and boost frequency
        if (mp.containsKey(key)) {
            Node node = mp.get(key);
            node.value = value;
            makeMostFrequentlyUsed(node);
        } 
        // Condition 2: Size is within capacity limits
        else if (currentSize < cap) {
            currentSize++;
            Node newNode = new Node(key, value);
            freq.computeIfAbsent(1, k -> new DoublyLinkedList()).pushFront(newNode);
            mp.put(key, newNode);
            minFreq = 1; // Resets minimum frequency state to 1 for the new element
        } 
        // Condition 3: Eviction path (Time to remove LFU or LRU if tie)
        else {
            DoublyLinkedList minFreqList = freq.get(minFreq);
            Node keyDelete = minFreqList.popBack(); // Pulls least recently used element from back
            
            mp.remove(keyDelete.key);
            
            Node newNode = new Node(key, value);
            freq.computeIfAbsent(1, k -> new DoublyLinkedList()).pushFront(newNode);
            mp.put(key, newNode);
            minFreq = 1; // Resets minimum frequency state to 1
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */