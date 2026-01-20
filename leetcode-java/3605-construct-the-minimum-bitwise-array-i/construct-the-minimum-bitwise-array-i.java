class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = getNum(nums.get(i));
        }
        return res;
    }

    private int getNum(int target) {
        for (int i = 0; i < target; i++) {
            if ((i | i + 1) == target) return i;
        }
        return -1;
    }
}