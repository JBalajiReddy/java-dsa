class Solution {
    public int[] findOrder(int V, int[][] prerequisites) {
        int[] inDegree = new int[V];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] p : prerequisites) {
            adj.get(p[0]).add(p[1]);
            inDegree[p[1]]++;
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        int cnt = 0, idx = V - 1;
        int[] res = new int[V];
        while (!q.isEmpty()) {
            int node = q.poll();
            res[idx--] = node;
            cnt++;
            for (int neigh : adj.get(node)) {
                inDegree[neigh]--;
                if (inDegree[neigh] == 0) {
                    q.offer(neigh);
                }
            }
        }

        return cnt == V ? res : new int[] {};
    }
}