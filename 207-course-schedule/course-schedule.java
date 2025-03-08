class Solution {
    
    public boolean checkCycle(int node, List<List<Integer>> graph, int[] color) {
        color[node] = 1; // Mark as "visiting"

        for (int neighbor : graph.get(node)) {
            if (color[neighbor] == 1) {
                // Cycle detected (back edge to a node in the recursion stack)
                return true;
            }
            if (color[neighbor] == 2) {
                // Already processed node (no need to check again)
                continue;
            }
            if (checkCycle(neighbor, graph, color)) {
                return true;
            }
        }

        color[node] = 2; // Mark as "fully processed"
        return false;
    }
    
    public boolean canFinish(int n, int[][] prerequisites) {
        // Build the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : prerequisites) {
            int a = edge[0];
            int b = edge[1];
            graph.get(b).add(a);  // Directed edge from b → a
        }

        int[] color = new int[n]; // 0 -> Unvisited, 1 -> Visiting, 2 -> Visited

        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (checkCycle(i, graph, color)) {
                    return false; // Cycle detected
                }
            }
        }

        return true; // No cycle found
    }
}






// class Solution {
//     public boolean canFinish(int numCourses, int[][] prerequisites) {
//         if (prerequisites == null || prerequisites.length == 0)
//             return true;

//         boolean[][] adj = new boolean[numCourses][numCourses];
//         for (int i = 0; i < prerequisites.length; i++) {
//             adj[prerequisites[i][0]][prerequisites[i][1]] = true;
//         }

//         boolean[] visited = new boolean[numCourses];
//         boolean[] explored = new boolean[numCourses];
//         for (int i = 0; i < numCourses; i++) {
//             if (!visited[i] && isCyclic(adj, i, visited, explored)) {
//                 return false;
//             }
//         }

//         return true;
//     }

//     boolean isCyclic(boolean[][] adj, int i, boolean[] visited, boolean[] explored) {
//         visited[i] = true;
//         for (int j = 0; j < adj.length; j++) {
//             if (adj[i][j]) {
//                 if (!visited[j]) {
//                     if (isCyclic(adj, j, visited, explored))
//                         return true;
//                 } else if (!explored[j])
//                     return true;
//             }
//         }
//         explored[i] = true;
//         return false;
//     }

// }
