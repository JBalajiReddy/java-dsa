class Pair {
    int node, w;
    Pair (int node, int w) {
        this.node = node;
        this.w = w;
    }
}

class Tuple {
    int steps, node, cost;
    Tuple (int steps, int node, int cost) {
        this.steps = steps;
        this.node = node;
        this.cost = cost; 
    }
}

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < times.length; i++) {
            int u = times[i][0];
            int v = times[i][1];
            int w = times[i][2];
            adj.get(u).add(new Pair(v, w));
        }

        int dist[] = new int[n + 1];
        Arrays.fill(dist, (int)(1e9));

        dist[k] = 0;
        Queue<Tuple> q = new ArrayDeque<>();
        q.offer(new Tuple(0, k, 0));

        while (!q.isEmpty()) {
            Tuple t = q.poll();
            int steps = t.steps;
            int node = t.node;
            int cost = t.cost;

            for (Pair p : adj.get(node)) {
                int neigh = p.node;
                int edgeW = p.w;

                if (cost + edgeW < dist[neigh]) {
                    dist[neigh] = cost + edgeW;
                    q.offer(new Tuple(steps + 1, neigh, dist[neigh]));
                }
            }
        }

        int mx = -1;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == (int)(1e9)) return -1;
            mx = Math.max(mx, dist[i]);
        }

        return mx;
    }
}