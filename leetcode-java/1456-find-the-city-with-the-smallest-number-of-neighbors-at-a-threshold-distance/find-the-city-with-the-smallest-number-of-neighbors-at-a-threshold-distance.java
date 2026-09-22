class Solution {
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