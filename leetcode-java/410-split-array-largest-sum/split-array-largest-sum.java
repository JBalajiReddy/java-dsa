class Solution {
    public int splitArray(int[] nums, int k) {
        int l = 0, r = 0;
        for (int num : nums) {
            l = Math.max(l, num);
            r += num;
        }

        int res = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (isPossible(nums, k, m)) {
                res = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return res;
    }

    private boolean isPossible(int[] nums, int k, int limit) {
        int cnt = 1, sum = 0;
        for (int n : nums) {
            sum += n;
            if (sum > limit) {
                cnt++;
                sum = n;
            }
        }
        return cnt <= k;
    }
}