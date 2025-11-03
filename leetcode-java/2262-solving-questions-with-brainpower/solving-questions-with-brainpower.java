class Solution {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1]; 

        for (int i = n - 1; i >= 0; i--) {
            int skip = Math.min(n, i + questions[i][1] + 1);
            long ans = questions[i][0] + dp[skip]; 
            long skipAns = dp[i + 1]; 
            dp[i] = Math.max(ans, skipAns); 
        }
        return dp[0]; 
    }
}
