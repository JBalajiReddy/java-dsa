class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 1. Build adjacency list: node -> list of {neighbor, flight_cost}
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] f : flights) {
            adj.get(f[0]).add(new int[] { f[1], f[2] });
        }

        // 2. Track minimum cost to reach each node
        int[] dist = new int[n];
        Arrays.fill(dist, (int) 1e9);
        dist[src] = 0;

        // 3. Standard Queue for BFS (ArrayDeque): processes paths level-by-level (by stops).
        // WHY NOT PRIORITY QUEUE? Since each edge = 1 stop, BFS naturally processes 
        // 0 stops -> 1 stop -> 2 stops in order without heap log(V) overhead.
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { 0, src, 0 }); // {stops, current_node, current_cost}

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int stops = curr[0];
            int node = curr[1];
            int cost = curr[2];

            // STOP CONDITION: If we exceed 'k' stops, don't explore neighbors further
            if (stops > k) {
                continue;
            }

            // DO NOT add 'if (cost > dist[node]) continue;' here!
            // A path with HIGHER cost might have FEWER stops, which could be the 
            // ONLY valid path that reaches 'dst' within 'k' stops limit.

            for (int[] arr : adj.get(node)) {
                int neigh = arr[0];
                int neighCost = arr[1];

                // Relax edge: Only queue neighbor if we found a cheaper cost within current stops
                if (cost + neighCost < dist[neigh]) {
                    dist[neigh] = cost + neighCost;
                    q.offer(new int[] { stops + 1, neigh, dist[neigh] });
                }
            }
        }

        return dist[dst] == (int) 1e9 ? -1 : dist[dst];
    }
}