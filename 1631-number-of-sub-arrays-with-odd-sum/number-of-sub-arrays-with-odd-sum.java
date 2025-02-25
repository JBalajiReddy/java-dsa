class Solution {
    public int numOfSubarrays(int[] arr) {
        int count = 0;
        int evenCount = 1;
        int oddCount = 0;
        int MOD = 1000000007;
        int sum = 0;

        for (int num : arr) {
            sum += num;
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