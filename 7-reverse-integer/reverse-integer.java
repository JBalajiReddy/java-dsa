class Solution {
    public int reverse(int x) {
        int rev = 0; // This will store the reversed number
        
        while (x != 0) {
            // Get the last digit
            int pop = x % 10;
            x /= 10; // Move to the next digit

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
            
            // --- If no overflow, build the reversed number ---
            rev = rev * 10 + pop;
        }
        
        return rev;
    }
}