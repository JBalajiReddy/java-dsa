class Solution {
    class DSU {
        int[] parent;
        int[] rank;
        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path Compression
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                // Union by Rank
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }

    public int latestDayToCross(int row, int col, int[][] cells) {
        int n = row * col;
        // DSU Size: All cells + Top Virtual Node + Bottom Virtual Node
        DSU dsu = new DSU(n + 2);
        int[][] grid = new int[row][col]; // 0 = Land, 1 = Water

        // 1. Initially, the entire grid is flooded (Water)
        for (int[] rowArr : grid) {
            java.util.Arrays.fill(rowArr, 1);
        }

        // Virtual Nodes indices
        int topVirtual = n;
        int bottomVirtual = n + 1;

        int[] dX = { 0, 0, 1, -1 };
        int[] dY = { 1, -1, 0, 0 };

        // 2. Iterate backwards from the last day
        for (int i = cells.length - 1; i >= 0; i--) {
            int r = cells[i][0] - 1; // Convert 1-based to 0-based
            int c = cells[i][1] - 1;

            // Turn this cell back into Land
            grid[r][c] = 0;
            int currentId = r * col + c;

            // 3. Connect to Virtual Nodes
            if (r == 0) {
                dsu.union(currentId, topVirtual);
            }
            if (r == row - 1) {
                dsu.union(currentId, bottomVirtual);
            }

            // 4. Connect to valid Land neighbors
            for (int k = 0; k < 4; k++) {
                int nr = r + dX[k];
                int nc = c + dY[k];

                if (nr >= 0 && nr < row && nc >= 0 && nc < col && grid[nr][nc] == 0) {
                    int neighborId = nr * col + nc;
                    dsu.union(currentId, neighborId);
                }
            }

            // 5. Check if Top is connected to Bottom
            if (dsu.find(topVirtual) == dsu.find(bottomVirtual)) {
                return i; // This is the day BEFORE the current cell blocked the path
            }
        }

        return 0;
    }
}

// class Solution {
//     public int latestDayToCross(int row, int col, int[][] cells) {
//         int low = 1, high = cells.length;
//         while (low <= high) {
//             int mid = (low + high) / 2;
//             if (canReach(mid, row, col, cells)) {
//                 low = mid + 1;
//             } else {
//                 high = mid - 1;
//             }
//         }
//         return low;
//     }

//     private boolean canReach(int day, int row, int col, int[][] cells) {
//         int[][] grid = new int[row][col];
//         for (int i = 0; i <= day; i++) {
//             int[] cell = cells[i];
//             int r = cell[0] - 1;
//             int c = cell[1] - 1;
//             grid[r][c] = 1;
//         }
//         Queue<int[]> q = new ArrayDeque<>();
//         boolean[][] vis = new boolean[row][col];
//         for (int i = 0; i < col; i++) {
//             if (grid[0][i] == 0) {
//                 q.add(new int[] { 0, i });
//                 vis[0][i] = true;
//             }
//         }

//         int[] dX = { -1, 0, 1, 0 }, dY = { 0, 1, 0, -1 };
//         while (!q.isEmpty()) {
//             int[] rc = q.poll();
//             int r = rc[0], c = rc[1];
//             for (int i = 0; i < 4; i++) {
//                 int X = r + dX[i], Y = c + dY[i];
//                 if (X < 0 || X >= row || Y < 0 || Y >= col || vis[X][Y] == true || grid[X][Y] == 1) {
//                     continue;
//                 }

//                 vis[X][Y] = true;
//                 if (X == row - 1)
//                     return true;
//                 q.add(new int[]{ X, Y });
//             }
//         }
//         return false;
//     }
// }