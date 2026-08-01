class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare((b[0] * b[0] + b[1] * b[1]), (a[0] * a[0] + a[1] * a[1])));

        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            pq.offer(new int[] {x, y});

            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[][] res = new int[k][2];
        int i = 0;
        while (!pq.isEmpty()) {
            res[i++] = pq.poll();
        }

        return res;
    }
}