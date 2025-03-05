class Solution {
    private int n;

    public int findCircleNum(int[][] isConnected) {
        n = isConnected.length;
        int provinces = 0;
        boolean[] visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                provinces++;
                dfs(i, isConnected, visit);
            }
        }
        return provinces;
    }

    public void dfs(int node, int[][] isConnected, boolean[] visit) {
        visit[node] = true;
        for (int i = 0; i < n; i++) {
            if (isConnected[node][i] == 1 && !visit[i]) {
                dfs(i, isConnected, visit);
            }
        }
    }
}