class Solution {
    public int subsetXORSum(int[] nums) {
        return sumXOR(nums, 0, 0);
    }

    private int sumXOR(int[] nums, int idx, int currXOR) {
        if (idx == nums.length)
            return currXOR;
        int withEle = sumXOR(nums, idx + 1, currXOR ^ nums[idx]);
        int withoutEle = sumXOR(nums, idx + 1, currXOR);
        return withEle + withoutEle;
    }
}