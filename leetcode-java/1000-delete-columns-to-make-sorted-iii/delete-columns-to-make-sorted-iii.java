class Solution {
    public int minDeletionSize(String[] strs) {
        int r = strs.length, c = strs[0].length();
        int[] dp = new int[c];
        Arrays.fill(dp, 1);
        int maxLis = 1;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < i; j++) {
                boolean sorted = true;
                for (int k = 0; k < r; k++) {
                    if (strs[k].charAt(j) > strs[k].charAt(i)) {
                        sorted = false;
                        break;
                    }
                }
                if (sorted) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLis = Math.max(maxLis, dp[i]);
        }
        return c - maxLis;
    }
}