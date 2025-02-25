class Solution {
    public int numOfSubarrays(int[] arr) {
        int count = 0;
        int evenCount = 1;
        int oddCount = 0;
        int MOD = 1000000007;
        int sum = 0;

        for (int num : arr) {
            sum += num;
            // If prefixSum is odd, the subarray sum from the start to the current index is
            // odd. To form another odd subarray, we need to subtract a previously seen even
            // prefix sum. So, we add the count of previously seen even prefix sums to our
            // answer.
            if (sum % 2 != 0) {
                count = (count + evenCount) % MOD;
                oddCount++;
            } else {
                count = (count + oddCount) % MOD;
                evenCount++;
            }
        }
        return count;
    }
}