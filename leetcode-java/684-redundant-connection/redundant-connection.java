class DSU {
    protected int[] parent;
    protected int[] rank;

    DSU(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    public boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px == py) {
            return false;
        }

        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else if (rank[py] < rank[px]) {
            parent[py] = px;
        } else {
            parent[px] = py;
            rank[py]++;
        }

        return true;
    }
}

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(edges.length);
        for (int[] e : edges) {
            // If union returns false, this edge creates a cycle
            if (!dsu.union(e[0], e[1])) {
                return e;
            }
        }

        return new int[0];
    }
}

class Solution_DFS {
    private boolean[] visit;
    private List<List<Integer>> adj;
    private Set<Integer> cycle;
    private int cycleStart;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        adj = new ArrayList<>();
        
        // 1-based indexing for graph representation
        for (int i = 0; i <= n; i++)
            adj.add(new ArrayList<>());

        // Build the complete undirected graph
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        visit = new boolean[n + 1];
        cycle = new HashSet<>();
        cycleStart = -1;

        // Run DFS starting from node 1 to locate cycle nodes
        dfs(1, -1);

        // Iterate backwards through edges to return the last edge that forms the cycle
        for (int i = edges.length - 1; i >= 0; i--) {
            int u = edges[i][0], v = edges[i][1];
            // An edge is part of the cycle if both endpoints belong to the cycle
            if (cycle.contains(u) && cycle.contains(v)) {
                return new int[]{u, v};
            }
        }
        return new int[0];
    }

    private boolean dfs(int node, int par) {
        // Base Case: If we revisit an already visited node, a cycle is found!
        if (visit[node]) {
            cycleStart = node; // Mark where the cycle loop originates
            return true;
        }

        visit[node] = true;

        for (int nei : adj.get(node)) {
            if (nei == par) continue; // Skip the immediate parent to avoid trivial back-tracking

            if (dfs(nei, node)) {
                // Backtracking phase: Add nodes to the cycle set as long as we are inside the cycle loop
                if (cycleStart != -1) cycle.add(node);

                // Stop adding nodes once we unwind back to where the cycle originally started
                if (node == cycleStart) {
                    cycleStart = -1;
                }
                return true;
            }
        }
        return false;
    }
}

class Solution_TopoSort {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] indegree = new int[n + 1]; // Tracks degrees of nodes in the undirected graph
        List<List<Integer>> adj = new ArrayList<>(n + 1);
        
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        // Build adjacency list and compute degree for every node
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            indegree[u]++;
            indegree[v]++;
        }

        // Add all leaf nodes (degree == 1) to the queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 1) q.offer(i);
        }

        // Iteratively trim leaf nodes from the graph (Kahn's Algorithm)
        while (!q.isEmpty()) {
            int node = q.poll();
            indegree[node]--; // Mark node as processed (degree becomes 0)

            for (int nei : adj.get(node)) {
                indegree[nei]--;
                // If neighbor becomes a leaf after removing 'node', queue it for removal
                if (indegree[nei] == 1) q.offer(nei);
            }
        }

        // After trimming all non-cycle branches, nodes participating in the cycle will have indegree > 0.
        // Scan backwards to find the last edge whose endpoints belong to the cycle.
        for (int i = edges.length - 1; i >= 0; i--) {
            int u = edges[i][0], v = edges[i][1];
            // Nodes remaining in the cycle will maintain indegree > 0 after leaf trimming
            if (indegree[u] > 0 && indegree[v] > 0)
                return new int[]{u, v};
        }
        return new int[0];
    }
}