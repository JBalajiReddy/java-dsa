class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Base case: Single node has no edges, height is 0, so node 0 is the root
        if (n == 1)
            return Collections.singletonList(0);

        // Build graph representation using adjacency list
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; ++i)
            adj[i] = new ArrayList<>();

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        // Track degree (number of connected edges) for each node
        int[] edge_cnt = new int[n];
        Queue<Integer> leaves = new LinkedList<>();

        // Identify initial leaf nodes (nodes with degree equal to 1)
        for (int i = 0; i < n; i++) {
            edge_cnt[i] = adj[i].size();
            if (adj[i].size() == 1)
                leaves.offer(i);
        }

        // Trim leaves layer-by-layer inward until at most 2 centroid nodes remain
        while (!leaves.isEmpty()) {
            // Once remaining nodes drop to 2 or 1, current leaves are the tree centroids
            if (n <= 2)
                return new ArrayList<>(leaves);

            int size = leaves.size();
            // Process all leaves at the current outer layer simultaneously
            for (int i = 0; i < size; ++i) {
                int node = leaves.poll();
                n--; // Remove current leaf from active node pool

                // Decrement connection count for neighbors
                for (int nei : adj[node]) {
                    edge_cnt[nei]--;
                    // If neighbor becomes a new leaf, queue it for the next trimming layer
                    if (edge_cnt[nei] == 1)
                        leaves.offer(nei);
                }
            }
        }

        return new ArrayList<>();
    }
}

// ----------------------------------------------------------------------------
// Brute-Force DFS Approach (Will cause Time Limit Exceeded / TLE)
// ----------------------------------------------------------------------------
class Solution_DFS {
    private List<List<Integer>> adj;

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        int minHgt = n;
        List<Integer> result = new ArrayList<>();

        // Run DFS starting from EVERY node to measure height (O(N^2) total time)
        for (int i = 0; i < n; i++) {
            int curHgt = dfs(i, -1);
            
            // Found another root with same minimum height
            if (curHgt == minHgt) {
                result.add(i);
            } 
            // Found a smaller minimum height; replace previous results
            else if (curHgt < minHgt) {
                result = new ArrayList<>();
                result.add(i);
                minHgt = curHgt;
            }
        }
        return result;
    }

    // Helper method to compute tree height recursively starting from node
    private int dfs(int node, int parent) {
        int hgt = 0;
        for (int nei : adj.get(node)) {
            // Avoid traversing back to parent node
            if (nei == parent) {
                continue;
            }
            hgt = Math.max(hgt, 1 + dfs(nei, node));
        }
        return hgt;
    }
}