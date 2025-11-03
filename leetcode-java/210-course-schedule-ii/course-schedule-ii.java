class Solution {
    public int[] findOrder(int V, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        //Apply - topological sort
        //BFS - kahn's algo
        int[] inDegree = new int[V];
        for (int p[] : prerequisites) {
            int u = p[0];
            int v = p[1];
            adj.get(v).add(u);
            inDegree[u]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        
        int cnt = 0;
        int[] res = new int[V];
        while (!q.isEmpty()) {
            int node = q.poll();
            res[cnt] = node;
            cnt++;

            for (int neigh : adj.get(node)) {
                inDegree[neigh]--;
                if (inDegree[neigh] == 0)
                    q.offer(neigh);
            }
        }
        return cnt == V ? res : new int[0];
    }
}