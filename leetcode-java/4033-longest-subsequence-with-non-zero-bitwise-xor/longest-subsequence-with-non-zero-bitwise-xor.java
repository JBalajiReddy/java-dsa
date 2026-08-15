class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int totalXor = 0;
        boolean allZero = true;

        // Step 1: Compute total XOR sum and check if any non-zero element exists
        for (int x : nums) {
            totalXor ^= x;
            if (x > 0) {
                allZero = false;
            }
        }

        // Step 2: If full array XOR is non-zero, full length 'n' is valid
        if (totalXor > 0) {
            return n;
        }

        // Step 3: If totalXor == 0:
        // - If array has at least one x > 0, removing x gives XOR = x (> 0), so length is n - 1.
        // - If all elements are 0, no non-zero subsequence can be formed, return 0.
        return allZero ? 0 : n - 1;
    }
}