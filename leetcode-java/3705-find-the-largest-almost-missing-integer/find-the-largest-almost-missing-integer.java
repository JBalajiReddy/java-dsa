class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // Count total frequencies of all elements in the array
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Case 1: k == n (Only 1 subarray exists)
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }

        // Case 2: k == 1 (Subarrays of size 1)
        if (k == 1) {
            int maxVal = -1;
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                if (entry.getValue() == 1) { // Appears exactly once globally
                    maxVal = Math.max(maxVal, entry.getKey());
                }
            }
            return maxVal;
        }

        // Case 3: 1 < k < n
        // Only boundary elements (nums[0] and nums[n-1]) can belong to exactly 1 subarray.
        int ans = -1;
        
        // Check nums[0]: valid only if it does not repeat elsewhere
        if (freq.get(nums[0]) == 1) {
            ans = Math.max(ans, nums[0]);
        }
        
        // Check nums[n - 1]: valid only if it does not repeat elsewhere
        if (freq.get(nums[n - 1]) == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }
}