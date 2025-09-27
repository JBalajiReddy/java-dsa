class Solution {
    /**
     * Calculates the length of the Longest Increasing Subsequence (LIS)
     * by finding the Longest Common Subsequence (LCS) between the original
     * array and a sorted, unique version of it.
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Step 1: Create a sorted array of the unique elements from `nums`.
        // A TreeSet is perfect for this as it stores unique elements in ascending order.
        Set<Integer> uniqueSortedSet = new TreeSet<>();
        for (int num : nums) {
            uniqueSortedSet.add(num);
        }

        // Convert the Set to an array to allow for indexed access.
        int[] sortedUniqueNums = new int[uniqueSortedSet.size()];
        int i = 0;
        for (Integer num : uniqueSortedSet) {
            sortedUniqueNums[i++] = num;
        }

        // Step 2: Find the length of the LCS between the original array
        // and the new sorted, unique array.
        return findLCSLength(nums, sortedUniqueNums);
    }

    /**
     * A standard dynamic programming function to find the length of the LCS
     * between two arrays.
     */
    private int findLCSLength(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;

        // dp[i][j] stores the length of the LCS of arr1[0...i-1] and arr2[0...j-1].
        int[][] dp = new int[n + 1][m + 1];

        // Build the DP table in a bottom-up manner.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // If the current characters match, they are part of the common subsequence.
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // If they don't match, take the maximum from the previous states.
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // The length of the LCS is in the bottom-right cell of the DP table.
        return dp[n][m];
    }
}


// class Solution {
//     public int lengthOfLIS(int[] nums) {
//         int n = nums.length;
//         if (n == 0) return 0;
//         int[][] memo = new int[n][n];
//         for (int[] row : memo) {
//             Arrays.fill(row, -1);
//         }
//         return f(0, 0, nums, memo);
//     }

//     private int f(int index, int prev_index, int[] nums, int[][] memo) {
//         if (index == nums.length) {
//             return 0;
//         }

//         if (memo[index][prev_index] != -1) {
//             return memo[index][prev_index];
//         }

//         if (prev_index < index) {
//             int take = 0;
//             if (nums[index] > nums[prev_index]) {
//                 take = 1 + f(index + 1, index, nums, memo);
//             }
//             int notTake = f(index + 1, prev_index, nums, memo);
//             return memo[index][prev_index] = Math.max(take, notTake);
//         } 
        
//         else {
//             // Option A: Start a new subsequence by taking nums[index].
//             int startHere = 1 + f(index + 1, index, nums, memo);
//             // Option B: Skip starting here and try to start from the next index.
//             int startLater = f(index + 1, index + 1, nums, memo);
//             return memo[index][prev_index] = Math.max(startHere, startLater);
//         }
//     }
// }

// Another approach which I found to be intuitive: We can store the elements of the array without duplicates in increasing order (Can be easily done with the help of TreeSet in java or set in cpp). Then again store these elements in a new array and find the LCS of the original array and the newly computed array. The LCS of these 2 arrays will be the LIS. For printing the LIS, we can use the same approach used for printing LCS.