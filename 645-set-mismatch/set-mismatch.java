class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] freq = new int[n + 1];
        for (int val : nums) {
            freq[val]++;
        }
        int num = -1, twice = -1;
        for (int i = 1; i < n + 1; i++) {
            if (freq[i] == 0) {
                num = i;
            }
            if (freq[i] == 2) {
                twice = i;
            }
            if (num != -1 && twice != -1)
                break;
        }

        return new int[] { twice, num };
    }
}