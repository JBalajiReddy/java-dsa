class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Long, Integer> mp = new HashMap<>();
        int cnt = 0;
        long currSum = 0;
        mp.put(0L, 1);
        for (int n : nums) {
            currSum += n;
            cnt += mp.getOrDefault(currSum - k, 0);
            mp.put(currSum, mp.getOrDefault(currSum, 0) + 1);
        }
        return cnt;
    }
}