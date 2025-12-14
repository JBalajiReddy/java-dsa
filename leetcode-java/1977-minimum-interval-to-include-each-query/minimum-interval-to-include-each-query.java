class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = queries.length;
        int[] res = new int[n];
        Integer[] queryIdx = new Integer[n];
        for (int i = 0; i < n; i++) {
            queryIdx[i] = i;
        }

        Arrays.sort(queryIdx, (a, b) -> Integer.compare(queries[a], queries[b])); //sort indx array based on queries
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); //sort on start interval

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1] - a[0], b[1] - b[0]));

        int intervalIdx = 0;

        for (int i = 0; i < n; i++) {
            int query = queries[queryIdx[i]];
            while (intervalIdx < intervals.length && intervals[intervalIdx][0] <= query) {
                int left = intervals[intervalIdx][0];
                int right = intervals[intervalIdx][1];
                pq.offer(new int[] { left, right });
                intervalIdx++;
            }

            while (!pq.isEmpty() && pq.peek()[1] < query) {
                pq.poll();
            }

            if (pq.isEmpty()) {
                res[queryIdx[i]] = -1;
            } else {
                res[queryIdx[i]] = pq.peek()[1] - pq.peek()[0] + 1;
            }
        }
        return res;
    }
}