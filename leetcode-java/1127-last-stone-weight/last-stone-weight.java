class Solution {
    public int lastStoneWeight(int[] stones) {
        int n = stones.length;
        if (n == 2) return Math.abs(stones[0] - stones[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int s : stones) pq.offer(s);
        while (pq.size() > 1) {
            int s1 = pq.poll();
            int s2 = pq.poll();
            if (s1 > s2) pq.offer(s1 - s2);
        }
        return !pq.isEmpty() ? pq.poll() : 0;
    }
}