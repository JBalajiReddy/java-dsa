class DSU {
    List<Integer> size = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();

    DSU(int n) {
        for (int i = 0; i <= n; i++) {
            size.add(1);
            parent.add(i);
        }
    }

    int findULTParent(int n) {
        if (parent.get(n) == n)
            return n;
        int ultp = findULTParent(parent.get(n));
        parent.set(n, ultp);
        return parent.get(n);
    }

    void unionBySize(int u, int v) {
        int ult_u = findULTParent(u);
        int ult_v = findULTParent(v);
        if (ult_u == ult_v)
            return;
        if (size.get(ult_u) > size.get(ult_v)) {
            parent.set(ult_v, ult_u);
            size.set(ult_u, size.get(ult_u) + size.get(ult_v));
        } else if (size.get(ult_u) < size.get(ult_v)) {
            parent.set(ult_u, ult_v);
            size.set(ult_v, size.get(ult_u) + size.get(ult_v));
        } else {
            parent.set(ult_v, ult_u);
            size.set(ult_u, size.get(ult_u) + size.get(ult_v));
        }
    }
}

class Solution {
    private boolean isValid(int adjr, int adjc, int n) {
        return adjr >= 0 && adjr < n && adjc >= 0 && adjc < n;
    }

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DSU dsu = new DSU(n * n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0)
                    continue;
                int[] dR = { -1, 0, 1, 0 };
                int[] dC = { 0, 1, 0, -1 };
                for (int idx = 0; idx < 4; idx++) {
                    int nR = i + dR[idx];
                    int nC = j + dC[idx];
                    if (isValid(nR, nC, n) && grid[nR][nC] == 1) {
                        int node = i * n + j;
                        int adjNode = nR * n + nC;
                        dsu.unionBySize(node, adjNode);
                    }
                }
            }
        }

        int mx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    continue;
                Set<Integer> comp = new HashSet<>();
                int[] dR = { -1, 0, 1, 0 };
                int[] dC = { 0, 1, 0, -1 };
                for (int idx = 0; idx < 4; idx++) {
                    int nR = i + dR[idx];
                    int nC = j + dC[idx];
                    if (isValid(nR, nC, n) && grid[nR][nC] == 1) {
                        int adjNode = nR * n + nC;
                        comp.add(dsu.findULTParent(adjNode));
                    }
                }
                int sizeTotal = 0;
                for (int parentNode : comp) {
                    sizeTotal += dsu.size.get(parentNode);
                }
                mx = Math.max(mx, sizeTotal + 1);
            }
        }
        
        //the grid is all 1's
        for (int cell = 0; cell < n * n; cell++) {
            mx = Math.max(mx, dsu.size.get(dsu.findULTParent(cell)));
        }

        return mx;
    }
}