class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (check(words[i], words[j]) && dp[j] + 1 > dp[i]) {
                    dp[i] = 1 + dp[j];
                }
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    private boolean check(String w1, String w2) {
        if (w1.length() != w2.length() + 1) return false;
        int p = 0, q = 0;
        while (p < w1.length()) {
            if (q < w2.length() && w1.charAt(p) == w2.charAt(q)) {
                p++;
                q++;
            } else {
                p++;
            }
        }
        return (p == w1.length() && q == w2.length());
    }
}