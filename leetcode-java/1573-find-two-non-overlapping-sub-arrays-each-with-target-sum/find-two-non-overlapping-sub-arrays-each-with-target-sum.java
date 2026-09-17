class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int res = Integer.MAX_VALUE;
        
        // DP Array: minLenTillIdx[k] stores the minimum length of a valid subarray ending at or before index k
        int[] minLenTillIdx = new int[n];
        Arrays.fill(minLenTillIdx, Integer.MAX_VALUE);
        
        int i = 0, j = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE; // Tracks shortest valid subarray found so far

        while (j < n) {
            // Step 1: Expand window to the right
            sum += arr[j];
            
            // Step 2: Shrink window from left if sum exceeds target
            while (i < j && sum > target) {
                sum -= arr[i++];
            }
            
            // Step 3: Check if current window matches target
            if (sum == target) {
                int len = j - i + 1;
                
                // If a valid non-overlapping subarray exists before index i, combine them
                if (i > 0 && minLenTillIdx[i - 1] != Integer.MAX_VALUE) {
                    res = Math.min(res, len + minLenTillIdx[i - 1]);
                }
                
                // Update shortest subarray length found up to current iteration
                minLen = Math.min(minLen, len);
            }
            
            // Step 4: Record best length seen up to index j
            minLenTillIdx[j] = minLen;
            j++;
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}