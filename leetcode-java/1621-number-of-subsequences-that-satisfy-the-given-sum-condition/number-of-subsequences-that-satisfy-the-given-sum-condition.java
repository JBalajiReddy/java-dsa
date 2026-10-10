import java.util.Arrays;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int numSubseq(int[] nums, int target) {
        int n = nums.length;

        // Sort so the smallest and largest values in a subsequence
        // can be checked using two pointers.
        Arrays.sort(nums);

        // power[i] stores 2^i % MOD.
        int[] power = new int[n];
        power[0] = 1;

        for (int i = 1; i < n; i++) {
            power[i] = (power[i - 1] * 2) % MOD;
        }

        int left = 0;
        int right = n - 1;
        int result = 0;

        while (left <= right) {
            // If the smallest and largest values fit within the target,
            // any subset of the elements between them can be selected.
            if (nums[left] + nums[right] <= target) {
                // There are 2^(right - left) ways to choose elements
                // between left and right, with nums[left] included.
                result = (result + power[right - left]) % MOD;
                left++;
            } else {
                // The largest value is too large, so exclude it.
                right--;
            }
        }

        return result;
    }
}
