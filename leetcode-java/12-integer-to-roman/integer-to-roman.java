class Solution {
    public String intToRoman(int num) {
        // 1. DEFINE VALUES AND ROMAN SYMBOLS IN DESCENDING ORDER
        // We explicitly include the 6 subtractive notation pairs (900, 400, 90, 40, 9, 4)
        // alongside the 7 standard Roman symbols. This creates 13 atomic building blocks.
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] strs = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        StringBuilder sb = new StringBuilder();

        // 2. GREEDY SUBTRACTION LOOP
        // Process from largest value to smallest value.
        // At every step, take the largest possible chunk out of 'num' to minimize 
        // the total number of Roman numeral characters used.
        for (int i = 0; i < values.length; i++) {
            // Keep subtracting the current value as long as 'num' is larger or equal
            while (num >= values[i]) {
                num -= values[i];
                sb.append(strs[i]); // Append corresponding Roman symbol
            }
        }

        return sb.toString();
    }
}