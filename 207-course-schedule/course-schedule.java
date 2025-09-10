class Solution {
    public boolean canFinish(int V, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        for (int p[] : prerequisites) {
            int u = p[0];
            int v = p[1];
            adj.get(u).add(v);
        }

        //Apply - topological sort
        //BFS - kahn's algo
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                int n = adj.get(i).get(j);
                inDegree[n]++;
            }
        }

        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            cnt++;

            for (int neigh : adj.get(node)) {
                inDegree[neigh]--;
                if (inDegree[neigh] == 0)
                    q.offer(neigh);
            }
        }
        return cnt == V;
    }
}