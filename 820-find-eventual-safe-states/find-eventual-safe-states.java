class Solution {
    private boolean dfs(int node, List<List<Integer>> adj, int[] vis, int[] path, int[] check) {
        vis[node] = 1;
        path[node] = 1;
        check[node] = 0;
        for (int neigh : adj.get(node)) {
            if (vis[neigh] == 0) {
                if (dfs(neigh, adj, vis, path, check)) {
                    return true;
                } 
            } else if (path[neigh] == 1) {
                return true;
            }
        }

        check[node] = 1;
        path[node] = 0;
        return false;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<List<Integer>> adj = new ArrayList<>();
        int V = graph.length;
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                adj.get(i).add(graph[i][j]);
            }
        }

        int[] vis = new int[V];
        int[] path = new int[V];
        int[] check = new int[V];
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) dfs(i, adj, vis, path, check);
        }

        for (int i = 0; i < V; i++) {
            if (check[i] == 1) res.add(i);
        }

        return res;
    }
}