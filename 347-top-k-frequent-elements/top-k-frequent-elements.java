class Solution {
    class Pair {
        int num, freq;

        Pair(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;

        Map<Integer, Integer> mp = new HashMap<>();
        for (int num : nums)
            mp.put(num, mp.getOrDefault(num, 0) + 1);

        PriorityQueue<Pair> pq = new PriorityQueue<>(
                (a, b) -> b.freq - a.freq);

        for (Map.Entry<Integer, Integer> entry : mp.entrySet())
            pq.offer(new Pair(entry.getKey(), entry.getValue()));

        int[] res = new int[k];

        for (int i = 0; i < k; i++)
            res[i] = pq.poll().num;
        return res;
    }
}