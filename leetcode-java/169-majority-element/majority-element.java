class Solution {
    public int majorityElement(int[] nums) {
        int count = 0, mE = 0;
        for (int num : nums) {
            if (count == 0)
                mE = num;

            count += (mE == num) ? 1 : -1;
        }
        return mE;
    }
}