class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i = 0;

        // Step 1: Cyclic Sort - Place each number x at index x - 1
        while (i < n) {
            int num = nums[i];

            // Ignore non-positive numbers and numbers larger than N
            // because they cannot fit into valid target indices [0, N-1].
            if (num <= 0 || num > n) {
                i++;
                continue;
            }

            int idx = num - 1; // Target index for the current number

            // If the element is not at its correct position, swap it.
            // Check nums[i] != nums[idx] to avoid infinite loops on duplicate values.
            if (nums[i] != nums[idx]) {
                int t = nums[i];
                nums[i] = nums[idx];
                nums[idx] = t;
                // Note: Do NOT increment 'i' here. The swapped element at nums[i] 
                // needs to be evaluated in the next iteration.
            } else {
                // Either nums[i] is already at index i (num == i + 1),
                // or it's a duplicate of the element at its target index.
                i++;
            }
        }

        // Step 2: Find the first index where the index matching breaks
        for (int j = 0; j < n; j++) {
            // The value at index j should be (j + 1)
            if (nums[j] != j + 1) {
                return j + 1; // Found the first missing positive integer
            }
        }

        // Step 3: If all numbers 1 to N are present in order, the answer is N + 1
        return n + 1;
    }
}