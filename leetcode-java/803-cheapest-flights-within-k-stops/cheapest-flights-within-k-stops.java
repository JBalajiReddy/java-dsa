class Pair {
    int v, w;
    Pair (int v, int w) {
        this.v = v;
        this.w = w;
    }
}

class Tuple {
    int stops, node, dist;
    Tuple(int stops, int node, int dist) {
        this.stops = stops;
        this.node = node;
        this.dist = dist;
    }
}

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < flights.length; i++) {
            int u = flights[i][0];
            int v = flights[i][1];
            int cost = flights[i][2];
            adj.get(u).add(new Pair(v, cost));
        }

        int dist[] = new int[n];
        Arrays.fill(dist, (int)(1e9));

        dist[src] = 0;
        Queue<Tuple> q = new ArrayDeque<>();
        q.offer(new Tuple(0, src, 0)); //stops, node, dist

        while (!q.isEmpty()) {
            Tuple t = q.poll();
            int stops = t.stops;
            int node = t.node;
            int cost = t.dist;

            if (stops > k) continue;
            for (Pair p : adj.get(node)) {
                int neigh = p.v;
                int edgeW = p.w;
                if (cost + edgeW < dist[neigh]) {
                    dist[neigh] = cost + edgeW;
                    q.offer(new Tuple(stops + 1, neigh, cost + edgeW));
                }
            }
        }

        return dist[dst] == (int)(1e9) ? -1 : dist[dst];
    }
}