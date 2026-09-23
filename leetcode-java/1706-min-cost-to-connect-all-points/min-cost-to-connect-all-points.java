class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[] minDist = new int[n];
        boolean[] inMST = new boolean[n];

        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0; // Start at point 0

        int res = 0;

        for (int step = 0; step < n; step++) {
            int u = -1;

            // Pick the unvisited node with the smallest distance to the current tree
            for (int i = 0; i < n; i++) {
                if (!inMST[i] && (u == -1 || minDist[i] < minDist[u])) {
                    u = i;
                }
            }

            inMST[u] = true;
            res += minDist[u];

            // Update distances to adjacent unvisited nodes
            for (int v = 0; v < n; v++) {
                if (!inMST[v]) {
                    int dist = Math.abs(points[u][0] - points[v][0]) + Math.abs(points[u][1] - points[v][1]);
                    minDist[v] = Math.min(minDist[v], dist);
                }
            }
        }

        return res;
    }
}

class Solution_Prims {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int minCost = 0;

        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        boolean[] vis = new boolean[n];

        // pq -> [cost, vertex]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Start with node 0
        pq.offer(new int[] { 0, 0 });
        minDist[0] = 0;

        int edgesConnected = 0;

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int cost = edge[0];
            int u = edge[1];

            if (vis[u])
                continue;

            vis[u] = true;
            minCost += cost;
            edgesConnected++;

            if (edgesConnected == n) {
                break;
            }

            for (int v = 0; v < n; v++) {
                if (!vis[v]) {
                    int dist = Math.abs(points[u][0] - points[v][0]) + Math.abs(points[u][1] - points[v][1]);
                    if (dist < minDist[v]) {
                        minDist[v] = dist;
                        pq.offer(new int[] { dist, v });
                    }
                }
            }
        }
        return minCost;
    }
}

class DSU {
    private int[] parent;
    private int[] rank;
    private int numOfComp;

    DSU(int n) {
        numOfComp = n;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int findParent(int X) {
        if (parent[X] == X) {
            return X;
        }
        return parent[X] = findParent(parent[X]);
    }

    public boolean union(int x, int y) {
        int parX = findParent(x);
        int parY = findParent(y);
        if (parX == parY) {
            return false;
        }

        if (rank[parX] < rank[parY]) {
            parent[parX] = parY;
        } else if (rank[parX] > rank[parY]) {
            parent[parY] = parX;
        } else {
            parent[parX] = parY;
            rank[parY]++;
        }
        numOfComp--;
        return true;
    }

    public int getNumOfComp() {
        return numOfComp;
    }
}

class Solution_Kruskals {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        DSU dsu = new DSU(n);

        List<int[]> edges = new ArrayList<>();
        //Generate all possible edges between pairs of points with their Manhattan distances.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new int[] { dist, i, j });
            }
        }

        edges.sort((a, b) -> a[0] - b[0]);
        int res = 0, edgesCnt = 0;
        for (int[] e : edges) {
            int dist = e[0], u = e[1], v = e[2];
            if (dsu.union(u, v)) {
                res += dist;
                edgesCnt++;
            }

            if (edgesCnt == n - 1) {
                break;
            }
        }

        return res;
    }
}