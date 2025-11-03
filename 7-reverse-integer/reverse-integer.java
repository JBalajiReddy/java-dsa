class Solution {
    public int reverse(int x) {
        boolean isNeg = x < 0 ? true : false;
        int y = Math.abs(x);

        long num = 0;
        while (y != 0) {
            int d = y % 10;
            num = num * 10 + d;
            y /= 10;
        }

        if (num >= Integer.MAX_VALUE || num < Integer.MIN_VALUE) return 0;
        return isNeg ? (int) -num : (int) num;
    }
}