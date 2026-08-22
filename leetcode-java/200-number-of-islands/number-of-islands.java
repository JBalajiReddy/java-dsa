class Solution {
    class DSU {
        int[] parent;
        int[] rank;
        int count; // Tracks total connected components (islands)

        public DSU(char[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            parent = new int[n * m];
            rank = new int[n * m];
            count = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == '1') {
                        int id = i * m + j;
                        parent[id] = id;
                        count++; // Count each '1' as an isolated island initially
                    }
                }
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            // Path compression
            return parent[i] = find(parent[i]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                // Union by rank
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                }
                count--; // Merged two islands into one
            }
        }

        public int getCount() {
            return count;
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int n = grid.length;
        int m = grid[0].length;
        DSU dsu = new DSU(grid);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0'; // Mark processed to avoid redundant union checks

                    // Only need to check Right and Down to cover all connections
                    if (i + 1 < n && grid[i + 1][j] == '1') {
                        dsu.union(i * m + j, (i + 1) * m + j);
                    }
                    if (j + 1 < m && grid[i][j + 1] == '1') {
                        dsu.union(i * m + j, i * m + (j + 1));
                    }
                }
            }
        }

        return dsu.getCount();
    }
}


class Solution_DFS {
    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length;
        int cnt = 0;
        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j] && grid[i][j] == '1') {
                    dfs(i, j, grid, vis);
                    ++cnt;
                }
            }
        }
        return cnt;
    }

    private void dfs(int i, int j, char[][] g, boolean[][] vis) {
        int n = g.length, m = g[0].length;
        if (i < 0 || i >= n || j < 0 || j >= m || g[i][j] == '0' || vis[i][j]) {
            return;
        }

        vis[i][j] = true;

        int[] dR = { 0, 1, 0, -1 }, dC = { 1, 0, -1, 0 };
        for (int k = 0; k < 4; k++) {
            dfs(i + dR[k], j + dC[k], g, vis);
        }
    }
}