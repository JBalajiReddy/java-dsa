/**
To solve this problem, we transform the given condition into a Maximum Sum Increasing Subsequence problem by defining a new key array where each element equals its value minus its index. The condition that a subsequence is balanced simplifies algebraically to checking that this new key is greater than or equal to the key of the preceding element in the sequence. We then iterate through the array, using a TreeMap to dynamically maintain each unique key alongside the maximum valid subsequence sum ending at that key. For every positive number, we use the map's floor query to instantly find and extend the largest compatible sum from previous indices in logarithmic time. Finally, after recording the new max sum for the current key, we prune any larger keys from the map that yield smaller or equal sums, keeping our lookup structure optimal and strictly monotonic.
*/

class Solution {
    public long maxBalancedSubsequenceSum(int[] nums) {
        int n = nums.length;
        
        // Track the global maximum single element (handles all-negative arrays)
        long maxVal = Long.MIN_VALUE;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        // If all numbers are negative or zero, the best sum is the single largest element
        if (maxVal <= 0) {
            return maxVal;
        }

        // TreeMap maps: arr[i] (nums[i] - i) -> max balanced sum ending with key arr[i]
        TreeMap<Integer, Long> map = new TreeMap<>();
        long ans = maxVal;

        for (int i = 0; i < n; i++) {
            // Skip non-positive elements as they never increase a positive subsequence sum
            if (nums[i] <= 0) {
                continue;
            }

            int key = nums[i] - i;
            long currentSum = nums[i];

            // Look for the largest sum among past elements with key <= current key
            Map.Entry<Integer, Long> floor = map.floorEntry(key);
            if (floor != null) {
                currentSum += floor.getValue();
            }

            // Maintain monotonicity: remove redundant future entries (key >= current key) 
            // that have a smaller or equal sum
            Map.Entry<Integer, Long> ceiling = map.ceilingEntry(key);
            while (ceiling != null && ceiling.getValue() <= currentSum) {
                map.remove(ceiling.getKey());
                ceiling = map.ceilingEntry(key);
            }

            // Only insert if it improves upon any existing floor entry
            if (floor == null || floor.getValue() < currentSum) {
                map.put(key, currentSum);
            }

            ans = Math.max(ans, currentSum);
        }

        return ans;
    }
}


//Approach-1 (Using LIS) - Recursion (TLE) ---> 316 / 345 testcases passed
//T.C : O(n^2) - prev index for every i
class Solution_LIS_TopDown_TLE {
    private Map<String, Long> memo = new HashMap<>();

    public long solve(int i, int prev, int[] nums) {
        if (i >= nums.length) {
            return 0;
        }

        String key = i + "_" + prev;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        long taken = Integer.MIN_VALUE;

        if (prev == -1 || nums[i] - i >= nums[prev] - prev) {
            taken = nums[i] + solve(i + 1, i, nums);
        }

        long notTaken = solve(i + 1, prev, nums);
        long result = Math.max(taken, notTaken);
        memo.put(key, result);

        return result;
    }

    public long maxBalancedSubsequenceSum(int[] nums) {
        boolean allNegative = true;
        long maxEl = Integer.MIN_VALUE;
        memo.clear();

        for (int x : nums) {
            maxEl = Math.max(maxEl, x);
            if (x >= 0) {
                allNegative = false;
            }
        }

        if (allNegative) {
            return maxEl;
        }

        return solve(0, -1, nums);
    }
}

//Approach-2 (Using LIS Bottom Up) - TLE (341/345 Test cases passed)
//Time : O(n^2)
class Solution_LIS_BOttomUP_TLE {
    public long maxBalancedSubsequenceSum(int[] nums) {
        int n = nums.length;

        int maxEl = Arrays.stream(nums).max().getAsInt();
        if (maxEl <= 0) {
            return maxEl;
        }

        long[] t = new long[n];
        for (int i = 0; i < n; i++) {
            t[i] = nums[i];
        }

        long maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] - i >= nums[j] - j) {
                    t[i] = Math.max(t[i], t[j] + nums[i]);
                    maxSum = Math.max(maxSum, t[i]);
                }
            }
        }

        return maxSum > maxEl ? maxSum : maxEl;
    }
}

// Approach-3 (Using Optimal LIS - Similar to Patience Sorting) - Accepted
// Time Complexity  : O(n log n)
// Space Complexity : O(n)
class Solution_DP_MonotonicMap {
    public long maxBalancedSubsequenceSum(int[] nums) {
        int n = nums.length;

        // Transform condition: nums[i] - i >= nums[j] - j  =>  arr[i] >= arr[j].
        // This converts the problem into finding a non-decreasing subsequence on 'arr'.
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i] - i;
        }

        // Stores map entries of (arr[i] -> max_subsequence_sum_ending_here).
        // TreeMap keeps keys sorted so we can query upper/lower bounds in O(log n).
        TreeMap<Integer, Long> map = new TreeMap<>();
        long ans = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            // If element is non-positive, it won't help extend any positive subsequence.
            // We only consider it on its own (for cases where all elements in 'nums' are <= 0).
            if (nums[i] <= 0) {
                ans = Math.max(ans, nums[i]);
            } else {
                long temp = nums[i];

                // Query the best predecessor: find the largest key <= arr[i].
                // If it exists, add its stored maximum sum to our current element.
                if (map.floorKey(arr[i]) != null) {
                    temp += map.get(map.floorKey(arr[i]));
                }

                // Prune dominated entries:
                // Remove any entry with key >= arr[i] that has a smaller sum than 'temp',
                // because (arr[i], temp) is strictly better (easier threshold + larger sum).
                while (map.ceilingKey(arr[i]) != null && map.get(map.ceilingKey(arr[i])) < temp) {
                    map.remove(map.ceilingKey(arr[i]));
                }

                // Insert into the map only if this new sum 'temp' improves upon 
                // the sum of the nearest existing smaller key (floorKey).
                if (map.floorKey(arr[i]) == null || map.get(map.floorKey(arr[i])) < temp) {
                    map.put(arr[i], temp);
                }

                // Keep track of the overall maximum subsequence sum found.
                ans = Math.max(ans, temp);
            }
        }
        return ans;
    }
}