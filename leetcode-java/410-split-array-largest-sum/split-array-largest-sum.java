class Solution {
    public int splitArray(int[] nums, int k) {
        int low = 0, high = 0;
        for (int n : nums) {
            low = Math.max(low, n);
            high += n;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(mid, nums, k) > k) {
                low = mid + 1; //not possible, increase limit
            } else {
                high = mid - 1; //possible limit, try to minimize
            }
        }
        return low;
    }

    private int isPossible(int limit, int[] arr, int k) {
        int sum = 0, cnt = 1;
        for (int i = 0; i < arr.length; i++) {
            if (sum + arr[i] <= limit) {
                sum += arr[i];
            } else {
                cnt++;
                sum = arr[i];
            }
        }
        return cnt;
    }
}