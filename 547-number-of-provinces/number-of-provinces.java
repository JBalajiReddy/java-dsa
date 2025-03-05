class Solution {
    private int n;

    public int findCircleNum(int[][] isConnected) {
        n = isConnected.length;
        int noOfComp = 0;
        boolean[] visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                noOfComp++;
                dfs(i, isConnected, visit);
            }
        }
        return noOfComp;
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