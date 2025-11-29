class Solution {
    public int[][] kClosest(int[][] points, int k) {
        //store points based on their distance
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            double distA = Math.sqrt(a[0] * a[0] + a[1] * a[1]);
            double distB = Math.sqrt(b[0] * b[0] + b[1] * b[1]);
            return Double.compare(distA, distB); 
        });

        for (int[] p : points) {
            pq.offer(p);
        }

        int[][] res = new int[k][2];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }

        return res;
    }
}
