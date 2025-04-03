class Solution {
    public long maximumTripletValue(int[] nums) {
        long res = 0, maxEle = 0, maxD = 0;
        for (int num : nums) {
            res = Math.max(res, maxD * num);
            maxD = Math.max(maxD, maxEle - num);
            maxEle = Math.max(maxEle, num);
        }
        return res;
    }
}