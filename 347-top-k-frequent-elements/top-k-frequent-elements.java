class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums)
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
            new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k)
                minHeap.poll(); // remove least frequent
        }

        int[] res = new int[k];
        int i = 0;
        while (!minHeap.isEmpty())
            res[i++] = minHeap.poll().getKey();

        return res;
    }
}



// class Solution {
//     class Pair {
//         int num, freq;

//         Pair(int num, int freq) {
//             this.num = num;
//             this.freq = freq;
//         }
//     }

//     public int[] topKFrequent(int[] nums, int k) {
//         int n = nums.length;

//         Map<Integer, Integer> mp = new HashMap<>();
//         for (int num : nums)
//             mp.put(num, mp.getOrDefault(num, 0) + 1);

//         PriorityQueue<Pair> pq = new PriorityQueue<>(
//                 (a, b) -> b.freq - a.freq);

//         for (Map.Entry<Integer, Integer> entry : mp.entrySet())
//             pq.offer(new Pair(entry.getKey(), entry.getValue()));

//         int[] res = new int[k];

//         for (int i = 0; i < k; i++)
//             res[i] = pq.poll().num;
//         return res;
//     }
// }