class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return solve(nums, k) - solve(nums, k - 1);
    }

    private int solve(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>();
        int left = 0, right = 0;
        int res = 0;

        while (right < n) {
            int num = nums[right];
            mp.put(num, mp.getOrDefault(num, 0) + 1);

            while (mp.size() > k) {
                mp.put(nums[left], mp.getOrDefault(nums[left], 0) - 1);
                if (mp.get(nums[left]) == 0)
                    mp.remove(nums[left]);

                left++;
            }


            if (mp.size() <= k)
                res += (right - left + 1);

            right++;
        }
        return res;
    }
}