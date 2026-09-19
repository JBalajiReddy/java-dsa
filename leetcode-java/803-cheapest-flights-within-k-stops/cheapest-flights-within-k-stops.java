// Helper class to store graph edges: neighbor node (v) and edge weight/cost (w)
class Pair {
    int v, w;
    Pair (int v, int w) {
        this.v = v;
        this.w = w;
    }
}

// Helper class to track state in the Queue for BFS traversal
class Tuple {
    int stops, node, dist;
    Tuple(int stops, int node, int dist) {
        this.stops = stops; // Number of intermediate stops made so far
        this.node = node;   // Current node index
        this.dist = dist;   // Total cost accumulated from source to this node
    }
}

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Step 1: Build the Adjacency List to represent the flight graph
        // adj.get(u) stores all outgoing flights from city 'u'
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < flights.length; i++) {
            int u = flights[i][0];
            int v = flights[i][1];
            int cost = flights[i][2];
            adj.get(u).add(new Pair(v, cost));
        }

        // Step 2: Initialize distance array to keep track of the minimum cost to reach each node.
        // Using 1e9 as a proxy for infinity to avoid integer overflow issues when adding edge weights.
        int dist[] = new int[n];
        Arrays.fill(dist, (int)(1e9));

        // Distance to starting node is zero
        dist[src] = 0;

        // Step 3: Use a standard Queue (BFS) because stops increase uniformly (+1 per step).
        // Since stops increase by 1 at each level, a standard Queue naturally processes 
        // routes in increasing order of stops (no PriorityQueue needed!).
        Queue<Tuple> q = new ArrayDeque<>();
        q.offer(new Tuple(0, src, 0)); // Starting state: 0 stops, source node, 0 cost

        // Step 4: BFS Traversal
        while (!q.isEmpty()) {
            Tuple t = q.poll();
            int stops = t.stops;
            int node = t.node;
            int cost = t.dist;

            // Gotcha check: If we have already exceeded 'k' stops, we cannot take any more edges from this node.
            // Note: 'k' represents the MAX allowed intermediate stops, meaning a path can have at most (k + 1) edges.
            if (stops > k) continue;

            // Explore all neighboring cities reachable from the current node
            for (Pair p : adj.get(node)) {
                int neigh = p.v;
                int edgeW = p.w;

                // Relaxation step: Update distance array if a cheaper path is found 
                // within the allowed number of stops
                if (cost + edgeW < dist[neigh]) {
                    dist[neigh] = cost + edgeW;
                    // Push the neighbor into queue with incremented stops count
                    q.offer(new Tuple(stops + 1, neigh, cost + edgeW));
                }
            }
        }

        // Step 5: Return result. If destination is still infinity, it's unreachable within 'k' stops.
        return dist[dst] == (int)(1e9) ? -1 : dist[dst];
    }
}