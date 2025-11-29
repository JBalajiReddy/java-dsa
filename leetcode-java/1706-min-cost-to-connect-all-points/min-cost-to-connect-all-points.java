class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int minCost = 0;
        //pq -> [cost, vertex]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); //sort based on cost
        Map<Integer, Integer> cache = new HashMap<>();
        boolean[] vis = new boolean[n];

        pq.offer(new int[] { 0, 0 });
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int cost = edge[0], u = edge[1];
            if (vis[u])
                continue;

            vis[u] = true;
            minCost += cost;
            for (int v = 0; v < n; v++) {
                if (!vis[v]) {
                    int dist = Math.abs(points[u][0] - points[v][0]) + Math.abs(points[u][1] - points[v][1]);
                    if (dist < cache.getOrDefault(v, Integer.MAX_VALUE)) {
                        cache.put(v, dist);
                        pq.offer(new int[] { dist, v });
                    }
                }
            }
        }
        return minCost;
    }
}