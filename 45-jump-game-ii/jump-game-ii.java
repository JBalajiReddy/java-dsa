class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n]; //reach n - 1

        for (int i = 1; i < n; i++)
            dp[i] = Integer.MAX_VALUE; //since we are standing in the first position, it is reachable by default without making any move

        for (int i = 0; i < n; i++) {
            for (int j = i; j <= i + nums[i] && j < n; j++) { 
                
                //From index i, you can jump to index j (where j is between i and i + nums[i])
                //We update dp[j] with the minimum jumps needed to reach j, which is either:
                //the previous value of dp[j]
                //or dp[i] + 1 (one jump from i)

                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[n - 1];
    }
}