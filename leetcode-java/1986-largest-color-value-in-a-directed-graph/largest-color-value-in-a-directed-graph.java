class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        char[] clrs = colors.toCharArray();
        int n = clrs.length;

        // Build the graph and calculate each node's in-degree.
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] inDegree = new int[n];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            inDegree[v]++;
        }

        // Queue nodes with no incoming edges.
        Queue<Integer> queue = new ArrayDeque<>();
        for (int u = 0; u < n; u++) {
            if (inDegree[u] == 0) {
                queue.offer(u);
            }
        }

        // dp[u][c] = maximum count of color c on a path ending at node u.
        int[][] dp = new int[n][26];
        int result = 0;
        int visited = 0;

        // Process nodes in topological order.
        while (!queue.isEmpty()) {
            int u = queue.poll();
            visited++;

            int color = clrs[u] - 'a';

            // Include the current node's color in paths ending at u.
            dp[u][color]++;
            result = Math.max(result, dp[u][color]);

            // Pass the best color counts from u to each neighbor.
            for (int v : graph[u]) {
                for (int c = 0; c < 26; c++) {
                    dp[v][c] = Math.max(dp[v][c], dp[u][c]);
                }

                // Add v once all its predecessors have been processed.
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        // If some nodes were not processed, the graph contains a cycle.
        return visited == n ? result : -1;
    }
}
