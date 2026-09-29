class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        // Step 1: Obtain 1D topological orderings for rows and columns independently.
        // rowOrder[i] = the number placed at row index i.
        // colOrder[j] = the number placed at column index j.
        int[] rowOrder = topoSort(rowConditions, k);
        int[] colOrder = topoSort(colConditions, k);

        // Step 2: Cycle Check.
        // If either topological sort fails (returns empty array), a cyclic dependency exists
        // making it impossible to satisfy all conditions.
        if (rowOrder.length == 0 || colOrder.length == 0) {
            return new int[0][0];
        }

        // Step 3: Fast Position Mapping.
        // Build direct lookups: number -> assigned index (0-indexed).
        int[] rowPos = new int[k + 1];
        int[] colPos = new int[k + 1];

        for (int i = 0; i < k; i++) {
            rowPos[rowOrder[i]] = i; // Value rowOrder[i] belongs at row i
            colPos[colOrder[i]] = i; // Value colOrder[i] belongs at col i
        }

        // Step 4: Construct the final k x k matrix in O(k) time.
        int[][] matrix = new int[k][k];
        for (int num = 1; num <= k; num++) {
            matrix[rowPos[num]][colPos[num]] = num;
        }

        return matrix;
    }

    /**
     * Helper to perform Kahn's Algorithm (BFS Topological Sort) on 1-based node values [1..k].
     */
    private int[] topoSort(int[][] edges, int k) {
        // Build Adjacency List for Directed Graph (u -> v means u must appear before v)
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            adj.add(new ArrayList<>());
        }

        // Track incoming edges count for each node (1 to k)
        int[] inDegree = new int[k + 1];
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            adj.get(u).add(v);
            inDegree[v]++;
        }

        // Enqueue all nodes with no prerequisite dependencies (in-degree == 0)
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= k; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        // Array to store the topological sequence
        int[] res = new int[k];
        int idx = 0;

        // BFS processing
        while (!q.isEmpty()) {
            int n = q.poll();
            res[idx++] = n; // Place node into sorted sequence

            // Decouple dependencies for outgoing neighbors
            for (int neigh : adj.get(n)) {
                if (--inDegree[neigh] == 0) {
                    q.offer(neigh); // Enqueue when all prerequisites are satisfied
                }
            }
        }

        // Cycle Check: If we processed all k nodes, it's a valid DAG. Otherwise, a cycle exists.
        return (idx == k) ? res : new int[0];
    }
}