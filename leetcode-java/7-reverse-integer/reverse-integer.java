class Solution {
    public int reverse(int x) {
        int rev = 0; 
        
        while (x != 0) {
            int pop = x % 10;
            x /= 10; 

            // --- Check for overflow BEFORE we multiply ---

            // 1. Positive Overflow Check
            // If rev > 214748364 (which is MAX_VALUE / 10), then
            // rev * 10 will definitely overflow.
            if (rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            // If rev == 214748364, it overflows only if the next
            // digit (pop) is greater than 7 (the last digit of MAX_VALUE).
            if (rev == Integer.MAX_VALUE / 10 && pop > 7) {
                return 0;
            }

            // 2. Negative Overflow Check
            // If rev < -214748364 (which is MIN_VALUE / 10), then
            // rev * 10 will definitely overflow.
            if (rev < Integer.MIN_VALUE / 10) {
                return 0;
            }
            // If rev == -214748364, it overflows only if the next
            // digit (pop) is less than -8 (the last digit of MIN_VALUE).
            if (rev == Integer.MIN_VALUE / 10 && pop < -8) {
                return 0;
            }
            
            rev = rev * 10 + pop; //no overflow
        }
        
        return rev;
    }
}