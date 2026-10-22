class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // Max-Heap: Higher frequencies come out first
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue());

        maxHeap.addAll(freqMap.entrySet());

        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            char ch = entry.getKey();
            int freq = entry.getValue();

            for (int i = 0; i < freq; i++) {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}

class Solution1 {
    public String frequencySort(String s) {
        // Step 1: Count character frequencies
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Min-Heap ordered by character frequency
        PriorityQueue<Map.Entry<Character, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> a.getValue() - b.getValue());

        // Step 3: Maintain heap state or push all entries
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
        }

        // Step 4: Reconstruct string by popping elements from heap
        // Note: Since minHeap pops least frequent first, we prepend or reverse, 
        // OR we can pop from minHeap and build the result string.
        StringBuilder sb = new StringBuilder();
        while (!minHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = minHeap.poll();
            char ch = entry.getKey();
            int freq = entry.getValue();

            // Insert highest frequency characters at the front or use Max-Heap
            for (int i = 0; i < freq; i++) {
                sb.append(ch);
            }
        }

        // Because Min-Heap pops lowest frequency first, reverse the string at the end
        return sb.reverse().toString();
    }
}