class Solution {
    public int myAtoi(String s) {
        int len = s.length();

        if (len == 0)
            return 0;

        int idx = 0;
        while (idx < len && s.charAt(idx) == ' ')
            idx++;

        if (idx == len)
            return 0;

        char ch;

        boolean isNeg = (ch = s.charAt(idx)) == '-';

        if (isNeg || ch == '+')
            idx++;

        final int maxLimit = Integer.MAX_VALUE / 10;
        int res = 0;

        while (idx < len && isDigit(ch = s.charAt(idx))) {
            int digit = ch - '0';

            if (res > maxLimit || (res == maxLimit && digit > 7))
                return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            res = (res * 10) + digit;
            idx++;
        }

        return isNeg ? -res : res;
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }
}