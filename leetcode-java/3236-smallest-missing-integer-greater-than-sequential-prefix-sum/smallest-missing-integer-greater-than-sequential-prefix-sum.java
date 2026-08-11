class Solution {
    public int missingInteger(int[] nums) {
        // Step 1: Calculate the sum of the longest sequential prefix starting at index 0
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // Sequential condition: must be strictly consecutive (nums[i] == nums[i - 1] + 1)
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                // Break immediately because the prefix ends at the first non-consecutive element
                break;
            }
        }

        // Step 2: Put all elements of nums into a Set for O(1) existence checks
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // Step 3: Find the smallest integer >= sum that is NOT present in nums
        while (set.contains(sum)) {
            sum++;
        }

        return sum;
    }
}