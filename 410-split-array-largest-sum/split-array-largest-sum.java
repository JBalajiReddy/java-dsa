class Solution {
    public int splitArray(int[] nums, int k) {
        int st = Arrays.stream(nums).max().orElse(0);
        int end = Arrays.stream(nums).sum();
        while (st <= end) {
            int mid = st + (end - st) / 2;
            int count = countSubarraysWithMaxSumLimit(nums, mid);
            //Check: Can we split the array into ≤ k subarrays such that no subarray sum exceeds mid?
            //If yes → try smaller max
            //If no → we need a bigger max sum
            if (count <= k)
                end = mid - 1;
            else
                st = mid + 1;
        }
        return st;
    }

    private int countSubarraysWithMaxSumLimit(int[] nums, int maxAllowedSum) {
        int c = 1;
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] <= maxAllowedSum)
                sum += nums[i];
            else {
                c += 1;
                sum = nums[i];
            }
        }
        return c;
    }
}