class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n == 3) {
            if (nums[0] + nums[1] + nums[2] == 0) {
                res.add(Arrays.asList(nums[0], nums[1], nums[2]));
                return res;
            } else
                return res;
        }

        Arrays.sort(nums);

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int j = i + 1;
            int k = n - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {

                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));

                while (j < k && nums[j] == nums[j + 1])
                    j++;
                while (j < k && nums[k] == nums[k - 1])
                    k--;

                j++;
                k--;
                
                } else if (sum < 0)
                    j++;
                else
                    k--;
            }
        }
        return res;
    }
}