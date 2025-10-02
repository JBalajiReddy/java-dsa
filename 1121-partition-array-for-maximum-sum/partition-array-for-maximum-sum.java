class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return f(0, arr, k, dp);
    }

    private int f(int i, int[] arr, int k, int[] dp) {
        if (i == arr.length)
            return 0;
        if (dp[i] != -1)
            return dp[i];
        int maxi = -1, maxNum = -1, len = 0;
        for (int j = i; j < Math.min(arr.length, i + k); j++) {
            len++;
            maxNum = Math.max(maxNum, arr[j]);
            int sum = len * maxNum + f(j + 1, arr, k, dp);
            maxi = Math.max(maxi, sum);
        }
        return dp[i] = maxi;
    }
}