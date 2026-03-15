class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> mp = new HashMap<>();
        int idx1 = 0, idx2 = 0;
        mp.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (mp.containsKey(target - nums[i])) {
                idx1 = mp.get(target - nums[i]);
                idx2 = i;
                break;
            }
            mp.put(nums[i], i);
        }
        return new int[] { idx1, idx2 };
    }
}