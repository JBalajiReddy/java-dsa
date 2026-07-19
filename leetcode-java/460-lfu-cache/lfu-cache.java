class LFUCache {
    private final int cap;
    private int minFreq;

    // key -> value mapping
    private final Map<Integer, Integer> vals;
    // key -> frequency mapping
    private final Map<Integer, Integer> counts;
    // freq -> LinkedHashSet of keys (ordered by insertion/access)
    private final Map<Integer, LinkedHashSet<Integer>> lists;

    public LFUCache(int capacity) {
        this.cap = capacity;
        this.minFreq = 0;
        this.vals = new HashMap<>();
        this.counts = new HashMap<>();
        this.lists = new HashMap<>();
    }

    public int get(int key) {
        if (!vals.containsKey(key)) {
            return -1;
        }

        // Fetch current frequency
        int count = counts.get(key);
        // Boost frequency count
        counts.put(key, count + 1);

        // Remove from current frequency list
        lists.get(count).remove(key);

        // If the lowest frequency list is empty, update the minFreq tracking pointer
        if (count == minFreq && lists.get(count).isEmpty()) {
            minFreq++;
        }

        // Add to the upgraded frequency list
        lists.computeIfAbsent(count + 1, k -> new LinkedHashSet<>()).add(key);

        return vals.get(key);
    }

    public void put(int key, int value) {
        if (cap <= 0)
            return;

        // Case 1: Key already exists, update value and boost frequency
        if (vals.containsKey(key)) {
            vals.put(key, value);
            get(key); // Reuses frequency boosting logic
            return;
        }

        // Case 2: Cache is full, evict the LFU element
        if (vals.size() >= cap) {
            // Get the first element in the minFreq set (this is the oldest/LRU tie-breaker element)
            int evict = lists.get(minFreq).iterator().next();

            // Purge the evicted item from all maps
            lists.get(minFreq).remove(evict);
            vals.remove(evict);
            counts.remove(evict);
        }

        // Case 3: Insert new key-value pair
        vals.put(key, value);
        counts.put(key, 1);
        minFreq = 1; // Reset min frequency to 1
        lists.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */