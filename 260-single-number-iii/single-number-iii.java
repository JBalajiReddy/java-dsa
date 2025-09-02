class Solution {
    public int[] singleNumber(int[] nums) {
        long xor = 0;
        for (int num : nums) xor ^= num; 
        long differIdx = (xor & (xor - 1)) ^ xor; //right most differing idx
        //two unique numebrs will have this idx different
        int b1 = 0;
        int b2 = 0;
        for (int num : nums) {
            if ((num & differIdx) != 0)  b1 ^= num;
            else b2 ^= num;
        }
        return new int[]{b1, b2};
    }
}