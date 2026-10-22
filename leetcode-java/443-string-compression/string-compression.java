class Solution {
    public int compress(char[] chars) {
        int n = chars.length;
        if (n == 1) return 1;

        int idx = 0; // Write pointer: tracks destination index for compressed output
        
        for (int i = 0; i < n; i++) {
            int count = 0;
            char curr = chars[i];

            // Count contiguous occurrences of the current character
            while (i < n && curr == chars[i]) {
                count++;
                i++;
            }

            // Write the character
            chars[idx++] = curr;

            // Write count digits if count is greater than 1
            if (count > 1) {
                String num = String.valueOf(count);
                for (char digit : num.toCharArray()) {
                    chars[idx++] = digit;
                }
            }

            // Compensate for the outer loop's i++ step
            i--; 
        }

        return idx; // Returns the new effective length of the array
    }
}