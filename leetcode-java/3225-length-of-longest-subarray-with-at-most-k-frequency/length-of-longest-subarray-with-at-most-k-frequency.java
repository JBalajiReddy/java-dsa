class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        int start = -1, end = 0;
        int maxLen = -1;
        for (end = 0; end < nums.length; end++) {
            mp.put(nums[end], mp.getOrDefault(nums[end], 0) + 1);

            while (mp.get(nums[end]) > k) {
                start++;
                mp.put(nums[start], mp.get(nums[start]) - 1);
            }

            maxLen = Math.max(maxLen, end - start);
        }

        return maxLen;
    }
}