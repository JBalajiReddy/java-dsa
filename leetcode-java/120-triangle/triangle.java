//BOTTOM UP n,n -> 0,0
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] next = new int[n];

        //base case - last row
        for (int j = 0; j < n; j++)
            next[j] = triangle.get(n - 1).get(j);

        for (int i = n - 2; i >= 0; i--) {
            int[] curr = new int[n];
            for (int j = i; j >= 0; j--) {

                int down = triangle.get(i).get(j) + next[j];
                int diagonal = triangle.get(i).get(j) + next[j + 1];

                curr[j] = Math.min(down, diagonal);
            }
            next = curr;
        }
        return next[0];
    }
}

//TOP DOWN 0,0 -> n,n
// class Solution {
//     public int minimumTotal(List<List<Integer>> triangle) {
//         int n = triangle.size();
//         int[][] dp = new int[n][n];
//         for (int[] r : dp) Arrays.fill(r, -1);
//         return solve(triangle, 0, 0, n - 1, dp);
//     }
//     private int solve(List<List<Integer>> t, int i, int j, int n, int[][] dp) {
//         if (i == n) return t.get(i).get(j);

//         if (dp[i][j] != -1) return dp[i][j];

//         int down = t.get(i).get(j) + solve(t, i + 1, j, n, dp); 
//         int diagonal = t.get(i).get(j) + solve(t, i + 1, j + 1, n, dp);

//         return dp[i][j] = Math.min(down, diagonal);
//     }
// }