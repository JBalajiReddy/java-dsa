class Solution {
    class Pair {
        int v;
        long c;
        Pair(int v, long c) {
            this.v = v;
            this.c = c;
        }
    }
    public int minCost(int n, int[][] edges) {
        List<List<int[]>> graph = new ArrayList<>();
        List<List<int[]>> rev = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            rev.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph.get(u).add(new int[]{ v, w });
            rev.get(v).add(new int[]{ u, 2 * w }); 
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Long.compare(a.c, b.c));
        pq.offer(new Pair(0, 0L));
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int node = p.v;
            long cost = p.c;
            if (cost > dist[node]) continue;
            if (node == n - 1) return (int) dist[node];
            for (int[] neigh : graph.get(node)) {
                int v = neigh[0], w = neigh[1];
                if (dist[node] + w < dist[v]) {
                    dist[v] = dist[node] + w;
                    pq.offer(new Pair(v, dist[v]));
                }
            }
            for (int[] neigh : rev.get(node)) {
                int v = neigh[0], w = neigh[1];
                if (dist[node] + w < dist[v]) {
                    dist[v] = dist[node] + w;
                    pq.offer(new Pair(v, dist[v]));
                }
            }
        }
        return -1;
    }
}