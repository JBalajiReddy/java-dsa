class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Long, Integer> mp = new HashMap<>();
        int cnt = 0;
        long currSum = 0;
        mp.put(0L, 1);
        for (int n : nums) {
            currSum += n;
            // We want a subarray ending here with sum 'k'.
            // Equation: currSum - oldSum = k  =>  oldSum = currSum - k
            // We check if we have seen 'currSum - k' before.

            //the value in the map tells us HOW MANY times 
            // that specific oldSum occurred. We add that count to our total.
            cnt += mp.getOrDefault(currSum - k, 0);
            
            mp.put(currSum, mp.getOrDefault(currSum, 0) + 1);
        }
        return cnt;
    }
}