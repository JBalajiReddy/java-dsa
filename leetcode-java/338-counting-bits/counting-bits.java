class Solution {
    public int[] countBits(int n) {
        int dp[] = new int[n + 1];
        for (int i = 1; i <= n; i++)
            dp[i] = dp[i >> 1] + (i & 1);
        return dp;
    }
}

// Alternate O(nlogn) approach to calculate numbers of 1's

// while(n > 0) {
//     n = n & n - 1;
//     c++;
// }