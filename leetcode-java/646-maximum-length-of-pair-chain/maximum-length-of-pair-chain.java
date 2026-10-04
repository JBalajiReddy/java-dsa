class Solution {
    public int findLongestChain(int[][] pairs) {
        // Sort pairs primarily by their ending coordinate
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int count = 0;
        int currentEnd = Integer.MIN_VALUE;
        
        for (int[] pair : pairs) {
            // If the current pair starts after the previous pair ends, select it
            if (currentEnd < pair[0]) {
                count++;
                currentEnd = pair[1]; // Update the end coordinate of the chain
            }
        }
        
        return count;
    }
}

class Solution_BottomUp {
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        if (n <= 1) return n;

        // Sort by start position (or end position)
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxChain = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // p2 ends before p1 starts
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            maxChain = Math.max(maxChain, dp[i]);
        }

        return maxChain;
    }
}