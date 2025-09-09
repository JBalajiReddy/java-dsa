class Solution {

    private boolean dfs(ArrayList<ArrayList<Integer>> adj, int node, int[] color, int currColor) {
        color[node] = currColor;
        for (int neigh : adj.get(node)) {
            if (color[neigh] == -1) {
                if (!dfs(adj, neigh, color, 1 - currColor)) {
                    return false;
                }
            } else if (color[neigh] == color[node]) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                adj.get(i).add(graph[i][j]);
            }
        }

        int[] color = new int[n];
        Arrays.fill(color, -1);
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (!dfs(adj, i, color, 0)) {
                    return false;
                }
            }
        }
        return true;
    }
}




// class Solution {

//     private boolean bfs(ArrayList<ArrayList<Integer>> adj, int start, int[] color) {
//         int V = adj.size();
//         Queue<Integer> q = new LinkedList<>();
//         q.offer(start);
//         color[start] = 0;

//         while (!q.isEmpty()) {
//             int node = q.poll();

//             for (Integer neigh : adj.get(node)) {
//                 if (color[neigh] == -1) {
//                     q.offer(neigh);
//                     color[neigh] = 1 - color[node];
//                 } else if (color[neigh] == color[node])
//                     return false;
//             }
//         }
//         return true;
//     }

//     public boolean isBipartite(int[][] graph) {
//         int n = graph.length;
//         ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
//         for (int i = 0; i < n; i++)
//             adj.add(new ArrayList<>());
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < graph[i].length; j++) {
//                 adj.get(i).add(graph[i][j]);
//             }
//         }

//         int[] color = new int[n];
//         Arrays.fill(color, -1);
//         for (int i = 0; i < n; i++) {
//             if (color[i] == -1) {
//                 if (bfs(adj, i, color) == false)
//                     return false;
//             }
//         }

//         return true;
//     }
// }