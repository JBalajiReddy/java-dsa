class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;

        // Step 1: Create reversed adjacency list and in-degree array for reversed graph
        List<List<Integer>> revAdj = new ArrayList<>();
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            revAdj.add(new ArrayList<>());
        }

        // Original edge: i -> neighbor
        // Reversed edge: neighbor -> i
        for (int i = 0; i < n; i++) {
            for (int neighbor : graph[i]) {
                revAdj.get(neighbor).add(i);
            }
            // Out-degree in original graph = In-degree in reversed graph
            indegree[i] = graph[i].length;
        }

        // Step 2: Queue all nodes with in-degree 0 (original terminal nodes)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 3: BFS (Kahn's Algorithm)
        List<Integer> safeNodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            safeNodes.add(node);

            // Process incoming edges in reversed graph (outgoing in original)
            for (int neighbor : revAdj.get(node)) {
                indegree[neighbor]--; // Remove edge
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 4: Return safe nodes in sorted order
        Collections.sort(safeNodes);
        return safeNodes;
    }
}

class SolutionDFS {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;

        // Step 1: Construct the adjacency list from the input graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                // Add the target neighbor node (graph[i][j]) to node i's adjacency list
                adj.get(i).add(graph[i][j]);
            }
        }

        // Step 2: Initialize state tracking arrays
        // vis[i]   -> Tracks if node i has been visited at least once across all DFS calls
        // path[i]  -> Tracks if node i is currently active in the ongoing recursion stack
        // check[i] -> Tracks if node i is determined to be safe (1) or part of/leading to a cycle (0)
        int[] vis = new int[n];
        int[] path = new int[n];
        int[] check = new int[n];

        // Step 3: Trigger DFS for all unvisited nodes
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                dfs(i, vis, path, check, adj);
            }
        }

        // Step 4: Collect all nodes marked as safe
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (check[i] == 1) {
                res.add(i);
            }
        }

        return res;
    }

    private boolean dfs(int node, int[] vis, int[] path, int[] check, List<List<Integer>> adj) {
        // Mark node as visited and add it to the active DFS recursion stack
        vis[node] = 1;
        path[node] = 1;
        check[node] = 0; // Assume unsafe until all paths from this node are validated

        // Explore all outgoing directed edges from the current node
        for (int neigh : adj.get(node)) {
            // Case 1: Neighbor is unvisited -> Perform recursive DFS
            if (vis[neigh] == 0) {
                if (dfs(neigh, vis, path, check, adj)) {
                    return true; // Cycle detected further down the path
                }
            }
            // Case 2: Neighbor is already in the current recursion stack -> Cycle detected!
            else if (path[neigh] == 1) {
                return true;
            }
        }

        // If no cycles were found in any path from this node, it is safe
        check[node] = 1; // Mark node as safe
        path[node] = 0; // Backtrack: remove node from current recursion stack
        return false; // No cycle present
    }
}