class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int maxLen = 0;
        int[][] dp = new int[arr.length][arr.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int curr = 0; curr < arr.length; curr++) {
            map.put(arr[curr], curr);

            for (int prev = 0; prev < curr; prev++) {
                int diff = arr[curr] - arr[prev];

                int prevIdx = map.getOrDefault(diff, -1);

                if (diff < arr[prev] && prevIdx >= 0) {
                    dp[prev][curr] = dp[prevIdx][prev] + 1;
                } else {
                    dp[prev][curr] = 2;
                }
                maxLen = Math.max(dp[prev][curr], maxLen);
            }
        }
        return maxLen > 2 ? maxLen : 0;
    }
}