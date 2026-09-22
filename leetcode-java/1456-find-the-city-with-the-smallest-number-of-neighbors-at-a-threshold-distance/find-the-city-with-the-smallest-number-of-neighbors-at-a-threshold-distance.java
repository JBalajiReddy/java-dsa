class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // 1. Build Adjacency List: node -> List of {neighbor, weight}
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1], wt = e[2];
            adj.get(u).add(new int[] { v, wt });
            adj.get(v).add(new int[] { u, wt });
        }

        int minReachable = Integer.MAX_VALUE;
        int res = -1;

        // 2. Run Dijkstra starting from every single city
        for (int src = 0; src < n; src++) {
            int reachableCount = getReachableCities(src, n, adj, distanceThreshold);

            // Tie-breaker: '<=' picks the larger node index if counts match
            if (reachableCount <= minReachable) {
                minReachable = reachableCount;
                res = src;
            }
        }

        return res;
    }

    private int getReachableCities(int src, int n, List<List<int[]>> adj, int distanceThreshold) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Min-heap storing {node, current_distance}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[] { src, 0 });

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];
            int d = current[1];

            // Skip stale state entries in priority queue
            if (d > dist[u])
                continue;

            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];

                // Relaxation step
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new int[] { v, dist[v] });
                }
            }
        }

        // Count reachable cities within distanceThreshold (excluding src itself)
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (i != src && dist[i] <= distanceThreshold) {
                count++;
            }
        }

        return count;
    }
}

class Solution_FloydWarshall {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] cost = new int[n][n];

        // 1. Initialize distance matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    cost[i][j] = 0;
                } else {
                    cost[i][j] = (int) 1e9;
                }
            }
        }

        // 2. Populate direct edge weights
        for (int[] e : edges) {
            int u = e[0], v = e[1], wt = e[2];
            cost[u][v] = wt;
            cost[v][u] = wt;
        }

        // 3. Floyd-Warshall Algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                }
            }
        }

        // 4. Find the target city
        int minCitiesCnt = (int) 1e9;
        int res = -1;

        for (int i = 0; i < n; i++) {
            int cityCnt = 0; // RESET for each city 'i'

            for (int j = 0; j < n; j++) {
                // Do NOT count the city itself
                if (i != j && cost[i][j] <= distanceThreshold) {
                    cityCnt++;
                }
            }

            // Tie-breaker: '<=' prefers the larger city index 'i'
            if (cityCnt <= minCitiesCnt) {
                minCitiesCnt = cityCnt;
                res = i;
            }
        }

        return res;
    }
}