class Solution {
    public int openLock(String[] deadends, String target) {
        // Step 1: Initialize the visited set with all deadend combinations.
        // Using a HashSet allows O(1) lookup time to check if a state is blocked or visited.
        Set<String> vis = new HashSet<>(Arrays.asList(deadends));

        // Edge Case: If the starting lock position ("0000") or the target position is a deadend,
        // it is impossible to solve the lock. Return -1 immediately.
        if (vis.contains(target) || vis.contains("0000")) {
            return -1;
        }

        // Step 2: Initialize the BFS Queue.
        // ArrayDeque is used for optimal queue performance (FIFO order for level-by-level traversal).
        Queue<String> q = new ArrayDeque<>();

        // Start state initialization
        q.offer("0000");
        vis.add("0000"); // Mark "0000" as visited so we don't loop back to it later

        int turns = 0; // Tracks the minimum number of turns (the depth/level of BFS traversal)

        // Step 3: Run Breadth-First Search (BFS)
        while (!q.isEmpty()) {
            // Process all nodes currently in the queue for the current turn level
            int size = q.size();

            for (int i = 0; i < size; i++) {
                String lock = q.poll();

                // Goal Check: If we reached the target string, return the current turn count.
                // Because BFS explores level-by-level, the first time we hit the target is guaranteed to be the shortest path.
                if (lock.equals(target)) {
                    return turns;
                }

                // Step 4: Explore all 8 valid adjacent combinations (moving each digit +1 or -1)
                for (String next : helper(lock)) {
                    // Only process combinations that are NOT deadends and have NOT been visited yet
                    if (!vis.contains(next)) {
                        vis.add(next); // Mark as visited BEFORE pushing to queue to avoid adding duplicate states
                        q.offer(next);
                    }
                }
            }
            // Increment turn count after completing exploration of the current level
            turns++;
        }

        // If the queue becomes empty and the target was never reached, return -1 (unreachable target)
        return -1;
    }

    /**
     * Helper method to generate all 8 possible lock combinations from a given 4-digit lock state.
     * Each of the 4 slots can be turned forward (+1) or backward (-1).
     */
    private List<String> helper(String lock) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            // 1. Move the i-th digit FORWARD (+1)
            char[] arr = lock.toCharArray();
            // Wrap-around math: '9' + 1 becomes '0'
            arr[i] = (char) (((arr[i] - '0' + 1) % 10) + '0');
            res.add(new String(arr));

            // 2. Move the i-th digit BACKWARD (-1)
            arr = lock.toCharArray();
            // Wrap-around math: '0' - 1 becomes '9' (+10 handles negative remainder in modular arithmetic)
            arr[i] = (char) (((arr[i] - '0' - 1 + 10) % 10) + '0');
            res.add(new String(arr));
        }

        return res;
    }
}