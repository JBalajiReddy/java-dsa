class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // Step 1: Initialize the adjacency list for the graph
        List<Integer>[] graph = new ArrayList[n];
        for (int vertex = 0; vertex < n; vertex++) {
            graph[vertex] = new ArrayList<>();
        }

        // Populate the undirected graph (both directions)
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int completeCount = 0;
        Set<Integer> visited = new HashSet<>();

        // Step 2: Iterate through all nodes to find unvisited components
        for (int vertex = 0; vertex < n; vertex++) {
            if (visited.contains(vertex))
                continue;

            // componentInfo[0] = V (vertices count), componentInfo[1] = Sum of degrees
            int[] componentInfo = new int[2];
            dfs(vertex, graph, visited, componentInfo);

            // Step 4: Validate if the component is complete
            // Handshake formula: Total degrees must equal V * (V - 1)
            if (componentInfo[0] * (componentInfo[0] - 1) == componentInfo[1]) {
                completeCount++;
            }
        }
        return completeCount;
    }

    private void dfs(
            int curr,
            List<Integer>[] graph,
            Set<Integer> visited,
            int[] componentInfo) {
        // Mark current node as visited so it's not processed in future components
        visited.add(curr);

        // Increment the number of vertices found in this component
        componentInfo[0]++;

        // Add the degree of the current vertex to the total edge-count metric
        componentInfo[1] += graph[curr].size();

        // Step 3: Recurse through all unvisited neighbors
        for (int next : graph[curr]) {
            if (!visited.contains(next)) {
                dfs(next, graph, visited, componentInfo);
            }
        }
    }
}