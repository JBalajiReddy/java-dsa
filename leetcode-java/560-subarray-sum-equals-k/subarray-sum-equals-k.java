class Solution {
    public int subarraySum(int[] nums, int k) {
        // Map to store the history of our running totals: [Prefix Sum -> Frequency]
        Map<Long, Integer> mp = new HashMap<>();
        
        // Base case: A prefix sum of 0 has occurred exactly 1 time before we start.
        // This ensures we correctly count valid subarrays that start from index 0.
        mp.put(0L, 1);
        
        int cnt = 0;       // Tracks the total number of valid subarrays found
        long currSum = 0;  // Tracks the running total of elements as we iterate
        
        for (int n : nums) {
            currSum += n; // Update the running prefix sum with the current element
            
            // Check if we've seen a previous prefix sum that equals (currSum - k).
            // If we have, it means the elements between that past point and now sum exactly to k.
            // We add the frequency of that past sum to our total count.
            cnt += mp.getOrDefault(currSum - k, 0);
            
            // Record the current running total in the map for future iterations.
            // If we've seen this exact total before, just increment its frequency.
            mp.put(currSum, mp.getOrDefault(currSum, 0) + 1);
        }
        
        return cnt;
    }
}