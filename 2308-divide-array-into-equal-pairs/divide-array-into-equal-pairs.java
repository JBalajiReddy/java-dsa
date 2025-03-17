class Solution {
    public boolean divideArray(int[] nums) {
        int n = nums.length;
        int[] freq = new int[501];
        for (int i = 0; i < n; i++) {
            freq[nums[i]]++;
        }
        for (int count : freq) {
            if (count % 2 != 0)
                return false;
        }
        return true;
    }
}