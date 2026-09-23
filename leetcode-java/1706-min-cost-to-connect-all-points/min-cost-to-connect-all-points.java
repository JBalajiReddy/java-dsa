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

class Solution {
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