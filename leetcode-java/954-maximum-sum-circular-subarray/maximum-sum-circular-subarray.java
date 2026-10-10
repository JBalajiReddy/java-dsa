/*At first we find the maximum subarray of the array with the help of kadane's algorithm.
After that we find the maximum subarray again but this time we change the sign of all the elements in the array and we again find the maximum subarray with the help of kadane's algorithm(which is actually be minimum subarray in the original array).
Then we simply add the sum of the maximum subarray(which is finded by changing the sign of the elements)with the total sum(this give us the maximum sum of the circular array)
Now we simply compare the original sub array's maximum sum and the circular subarray maximum sum and return the maximum of both of it.*/

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int maxSubarraySum = Integer.MIN_VALUE;
        int currentSum = 0;

        // Standard Kadane's algorithm: find the maximum non-circular subarray.
        for (int num : nums) {
            currentSum += num;
            maxSubarraySum = Math.max(maxSubarraySum, currentSum);

            // Start a new subarray if the current sum is negative.
            if (currentSum < 0) {
                currentSum = 0;
            }
        }

        // Calculate the sum of all elements.
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int maxNegatedSubarraySum = Integer.MIN_VALUE;
        currentSum = 0;

        // Run Kadane's on negated values.
        // This finds the negation of the minimum-sum subarray.
        for (int num : nums) {
            currentSum += -num;
            maxNegatedSubarraySum =
                    Math.max(maxNegatedSubarraySum, currentSum);

            // Start a new subarray if the current sum is negative.
            if (currentSum < 0) {
                currentSum = 0;
            }
        }

        // Removing the minimum-sum subarray leaves the circular subarray.
        int circularSum = totalSum + maxNegatedSubarraySum;

        // If the minimum subarray is the entire array, circularSum would be 0,
        // representing an invalid empty subarray. Return the regular maximum.
        if (circularSum == 0) {
            return maxSubarraySum;
        }

        return Math.max(maxSubarraySum, circularSum);
    }
}


class Solution1 {

    // Finds the maximum sum of a non-circular subarray.
    private int kadanesMax(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Either extend the current subarray or start a new one.
            currentSum = Math.max(currentSum + nums[i], nums[i]);

            // Save the best maximum subarray sum seen so far.
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    // Finds the minimum sum of a contiguous subarray.
    private int kadanesMin(int[] nums) {
        int currentSum = nums[0];
        int minSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Either extend the current subarray or start a new one.
            currentSum = Math.min(currentSum + nums[i], nums[i]);

            // Save the best minimum subarray sum seen so far.
            minSum = Math.min(minSum, currentSum);
        }

        return minSum;
    }

    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;

        // Calculate the sum of all elements.
        for (int num : nums) {
            totalSum += num;
        }

        int maxSum = kadanesMax(nums);
        int minSum = kadanesMin(nums);

        // Removing the minimum subarray leaves the maximum circular subarray.
        int circularSum = totalSum - minSum;

        // If all values are negative, circularSum would represent an empty subarray.
        if (maxSum <= 0) {
            return maxSum;
        }

        // Choose the better of the regular and circular maximum subarrays.
        return Math.max(maxSum, circularSum);
    }
}


class Solution2 {
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = nums[0];

        // Track the current and best minimum-subarray sums.
        int currentMin = nums[0];
        int minSum = nums[0];

        // Track the current and best maximum-subarray sums.
        int currentMax = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            // Add the current value to the total array sum.
            totalSum += num;

            // Update the minimum subarray ending at this index.
            currentMin = Math.min(currentMin + num, num);
            minSum = Math.min(minSum, currentMin);

            // Update the maximum subarray ending at this index.
            currentMax = Math.max(currentMax + num, num);
            maxSum = Math.max(maxSum, currentMax);
        }

        // Removing the minimum subarray leaves the maximum circular subarray.
        int circularSum = totalSum - minSum;

        // If all values are negative, avoid returning an empty circular subarray.
        if (maxSum <= 0) {
            return maxSum;
        }

        // Return the better of the regular and circular maximum subarrays.
        return Math.max(maxSum, circularSum);
    }
}
