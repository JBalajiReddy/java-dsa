class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        // mat[i][j] will be true if course 'i' is a direct or indirect prerequisite for course 'j'
        boolean[][] mat = new boolean[n][n];

        // 1. Mark direct prerequisites
        for (int[] pre : prerequisites) {
            int u = pre[0];
            int v = pre[1];
            mat[u][v] = true;
        }

        // 2. Transitive closure using Floyd-Warshall algorithm
        // Note: All loops must iterate over 'n' (the total number of nodes)
        for (int k = 0; k < n; k++) { // Intermediate node
            for (int s = 0; s < n; s++) { // Source node
                for (int d = 0; d < n; d++) { // Destination node
                    mat[s][d] = mat[s][d] || (mat[s][k] && mat[k][d]);
                }
            }
        }

        // 3. Answer all queries in O(1) time each using the precomputed matrix
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            int u = query[0];
            int v = query[1];
            res.add(mat[u][v]);
        }

        return res;
    }
}

class Solution_TopologicalSort {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // Build adjacency list for graph representation and a prerequisite tracker:
        // isPrereq.get(i) stores ALL direct & indirect prerequisites for course 'i'
        List<Set<Integer>> adj = new ArrayList<>();
        List<Set<Integer>> isPrereq = new ArrayList<>();
        int[] indegree = new int[numCourses]; // Tracks incoming edges for Kahn's algorithm

        for (int i = 0; i < numCourses; i++) {
            adj.add(new HashSet<>());
            isPrereq.add(new HashSet<>());
        }

        // Build directed graph: pre[0] -> pre[1] (pre[0] is required before taking pre[1])
        for (int[] pre : prerequisites) {
            adj.get(pre[0]).add(pre[1]);
            indegree[pre[1]]++;
        }

        // Add all nodes with 0 in-degree (courses with no prerequisites) to queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        // Process graph in topological order
        while (!q.isEmpty()) {
            int node = q.poll();

            for (int neighbor : adj.get(node)) {
                // 1. Direct prerequisite: 'node' is a prerequisite for 'neighbor'
                isPrereq.get(neighbor).add(node);

                // 2. Transitive prerequisite: Everything required for 'node' is also required for 'neighbor'
                isPrereq.get(neighbor).addAll(isPrereq.get(node));

                // Standard Kahn's decrement logic
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    q.offer(neighbor); // Fully processed predecessors, ready to enter queue
                }
            }
        }

        // Answer queries in O(1) time using lookup set
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            int u = query[0]; // Is u a prerequisite...
            int v = query[1]; // ...for v?
            
            // Check if u is inside v's prerequisite set
            res.add(isPrereq.get(v).contains(u));
        }

        return res;
    }
}

class Solution_DFS {
    private List<Integer>[] adj;
    // Memoization table: 
    // -1 = unvisited state, 1 = true (prerequisite exists), 0 = false (no path found)
    private int[][] isPrereq;

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        adj = new ArrayList[numCourses];
        isPrereq = new int[numCourses][numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
            Arrays.fill(isPrereq[i], -1); // Initialize all subproblems as uncalculated
        }

        // Build REVERSE directed graph: child -> parent (crs -> prerequisite)
        // Traversing backwards from target course to look for source prerequisite
        for (int[] pre : prerequisites) {
            adj[pre[1]].add(pre[0]);
            isPrereq[pre[1]][pre[0]] = 1; // Mark direct edge directly in matrix
        }

        // Process each query dynamically using memoized DFS
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            int prereq = query[0];
            int crs = query[1];
            res.add(dfs(crs, prereq));
        }

        return res;
    }

    private boolean dfs(int crs, int prereq) {
        // Base case: Return cached result if subproblem (crs -> prereq) has already been solved
        if (isPrereq[crs][prereq] != -1) {
            return isPrereq[crs][prereq] == 1;
        }

        // Search through immediate incoming prerequisites of 'crs'
        for (int parent : adj[crs]) {
            // If direct parent is the target prerequisite OR target is reachable via parent
            if (parent == prereq || dfs(parent, prereq)) {
                isPrereq[crs][prereq] = 1; // Cache success state
                return true;
            }
        }

        isPrereq[crs][prereq] = 0; // Cache failure state (no path found)
        return false;
    }
}