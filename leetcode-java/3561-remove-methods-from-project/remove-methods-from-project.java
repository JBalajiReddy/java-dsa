class Solution {
    // DFS helper method to explore all methods directly or indirectly called by method 'u'
    public void func(int u, List<List<Integer>> adj, boolean[] vis) {
        vis[u] = true; // Mark current method as visited (suspicious)

        for (int v : adj.get(u)) {
            if (vis[v]) continue; // Skip if already visited to prevent infinite loops/cycles
            func(v, adj, vis);    // Recursively visit invoked method
        }
    }

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Step 1: Build the directed graph using an adjacency list
        // adj.get(u) contains all methods 'v' that method 'u' invokes (u -> v)
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] it : invocations) {
            int u = it[0];
            int v = it[1];
            adj.get(u).add(v);
        }

        // Step 2: Perform DFS starting from method 'k' to find all reachable (suspicious) methods
        boolean[] vis = new boolean[n];
        func(k, adj, vis);

        // Step 3: Validate if removing suspicious methods is safe
        // Check if any non-suspicious method (vis[u] == false) calls a suspicious method (vis[v] == true)
        for (int[] it : invocations) {
            int u = it[0];
            int v = it[1];

            // If the caller 'u' is already suspicious, this edge is fine (internal or outbound call)
            if (vis[u]) continue;

            // Violation: An external non-suspicious method 'u' depends on suspicious method 'v'
            // Removing 'v' would break 'u', so NO methods can be removed.
            if (vis[v]) {
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    ans.add(i); // Return all methods [0, 1, ..., n-1]
                }
                return ans;
            }
        }

        // Step 4: Safe to remove! Collect and return only the non-suspicious methods
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!vis[i]) { // Keep methods that were never reached from 'k'
                ans.add(i);
            }
        }

        return ans;
    }
}