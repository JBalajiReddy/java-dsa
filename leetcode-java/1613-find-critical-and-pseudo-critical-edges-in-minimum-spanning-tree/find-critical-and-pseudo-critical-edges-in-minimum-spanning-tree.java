class DSU {
    private int[] rank;
    private int[] parent;
    private int numOfComp;

    DSU(int n) {
        numOfComp = n;
        rank = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int findParent(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = findParent(parent[x]); // Path compression
    }

    public boolean union(int x, int y) {
        int px = findParent(x);
        int py = findParent(y);
        if (px == py)
            return false;

        // Union by rank
        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else if (rank[py] < rank[px]) {
            parent[py] = px;
        } else {
            parent[px] = py;
            rank[py]++;
        }
        numOfComp--;
        return true;
    }

    public boolean isSingleComponent() {
        return numOfComp == 1;
    }
}

class Solution {
    public int kruskal(int n, int[][] edges, int add_edge, int skip_edge) {
        DSU dsu = new DSU(n);
        int mst = 0;

        // Force add an edge first if requested
        if (add_edge != -1) {
            dsu.union(edges[add_edge][0], edges[add_edge][1]);
            mst += edges[add_edge][2];
        }

        // Standard Kruskal's MST loop
        for (int i = 0; i < edges.length; i++) {
            if (skip_edge == i) {
                continue; // Skip the specified edge
            }

            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];

            int parent_u = dsu.findParent(u);
            int parent_v = dsu.findParent(v);

            if (parent_u != parent_v) {
                dsu.union(u, v);
                mst += wt;
            }
        }

        // If the graph remains disconnected, return infinity
        if (!dsu.isSingleComponent()) {
            return Integer.MAX_VALUE;
        }

        return mst;
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int r = edges.length;
        int[][] newE = new int[r][4];

        // Preserve original index: [u, v, weight, original_index]
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < 3; j++) {
                newE[i][j] = edges[i][j];
            }
            newE[i][3] = i;
        }

        // Sort edges by weight
        Arrays.sort(newE, (a, b) -> a[2] - b[2]);

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>()); // Critical edges
        res.add(new ArrayList<>()); // Pseudo-critical edges

        // 1. Get baseline weight with NO forced/skipped edges
        int MST = kruskal(n, newE, -1, -1);

        for (int i = 0; i < r; i++) {
            // 2. Try building MST WITHOUT edge 'i'
            if (kruskal(n, newE, -1, i) > MST) {
                res.get(0).add(newE[i][3]); // Critical
            }
            // 3. Try building MST FORCE-INCLUDING edge 'i'
            else if (kruskal(n, newE, i, -1) == MST) {
                res.get(1).add(newE[i][3]); // Pseudo-Critical
            }
        }
        return res;
    }
}