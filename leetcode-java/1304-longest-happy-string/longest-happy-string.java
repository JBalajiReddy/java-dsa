class Solution {
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();

        // Max-heap ordered by remaining frequency
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
            (e1, e2) -> e2.getValue() - e1.getValue()
        );

        if (a > 0) pq.offer(Map.entry('a', a));
        if (b > 0) pq.offer(Map.entry('b', b));
        if (c > 0) pq.offer(Map.entry('c', c));

        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> first = pq.poll();
            int len = sb.length();

            // Check if adding 'first' creates 3 consecutive identical characters
            if (len >= 2 && sb.charAt(len - 1) == first.getKey() && sb.charAt(len - 2) == first.getKey()) {
                
                // We cannot use 'first'. Check if a second choice exists.
                if (pq.isEmpty()) {
                    break; // No valid character left to pick
                }

                Map.Entry<Character, Integer> second = pq.poll();
                
                // Append 1 instance of the second choice to break the streak
                sb.append(second.getKey());
                
                // Put 'second' back if count remains
                if (second.getValue() - 1 > 0) {
                    pq.offer(Map.entry(second.getKey(), second.getValue() - 1));
                }

                // Put 'first' back since we didn't use it in this step
                pq.offer(first);

            } else {
                // Safe to append 1 instance of the most frequent character
                sb.append(first.getKey());

                if (first.getValue() - 1 > 0) {
                    pq.offer(Map.entry(first.getKey(), first.getValue() - 1));
                }
            }
        }

        return sb.toString();
    }
}