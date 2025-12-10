class Solution {
    public int countPermutations(int[] complexity) {
        int n = complexity.length;
        int T = complexity[0];
        for (int i = 1; i < n; i++) {
            if (complexity[i] <= T)
                return 0;
        }

        int ans = 1;
        int MOD = 1000_000_007;
        for (int i = 2; i < n; i++) {
            ans = (int) (((long) ans * i) % MOD);
        }
        return ans;
    }
}