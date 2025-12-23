class Solution {
    public int maxTwoEvents(int[][] events) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int maxPrevVal = 0, maxSum = 0;
        for (int[] e : events) {
            while (!pq.isEmpty() && pq.peek()[1] < e[0]) {
                maxPrevVal = Math.max(maxPrevVal, pq.peek()[2]);
                pq.poll();
            }
            maxSum = Math.max(maxSum, maxPrevVal + e[2]);
            pq.offer(e);
        }
        return maxSum;
    }
}