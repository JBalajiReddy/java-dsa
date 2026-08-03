import java.util.*;

class Solution {
    public String reorganizeString(String s) {
        int n = s.length();

        // Step 1: Count frequency of each character in the string
        Map<Character, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            // getOrDefault returns current count if present, else 0
            mp.put(ch, mp.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Create a Max-Heap (Priority Queue) to store character frequencies
        // Lambda comparator `(b - a)` orders entries so the highest count is at the top
        PriorityQueue<Map.Entry<Character, Integer>> pq =
            new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        // Map.entrySet() converts map key-value pairs into a Set for the queue
        pq.addAll(mp.entrySet());

        // Step 3: Feasibility check
        // If the most frequent character occurs more than (n + 1) / 2 times,
        // it's mathematically impossible to arrange without adjacent duplicates
        if (pq.peek().getValue() > (n + 1) / 2) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        // Tracks the character used in the previous step to prevent consecutive placements
        Map.Entry<Character, Integer> prev = null;

        // Step 4: Greedily process characters using the Max-Heap
        while (!pq.isEmpty()) {
            // Pick the character with the highest remaining frequency
            Map.Entry<Character, Integer> curr = pq.poll();
            sb.append(curr.getKey());

            // If the previously used character still has remaining count,
            // re-insert it back into the heap now (since 1 step has passed)
            if (prev != null && prev.getValue() > 0) {
                pq.offer(prev);
            }

            // Temporarily hold 'curr' out of the heap with its count decremented by 1
            // SimpleEntry creates a concrete key-value pair object
            prev = new AbstractMap.SimpleEntry<>(curr.getKey(), curr.getValue() - 1);
        }

        return sb.toString();
    }
}