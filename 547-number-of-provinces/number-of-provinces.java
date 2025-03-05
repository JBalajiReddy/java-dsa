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

    public void dfs(int city, int[][] isConnected, boolean[] visit) {
        visit[city] = true;
        for (int i = 0; i < n; i++) {
            if (isConnected[city][i] == 1 && !visit[i]) {
                dfs(i, isConnected, visit);
            }
        }
    }
}