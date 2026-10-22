class Solution {
    public int compress(char[] chars) {
        int n = chars.length;
        if (n == 1) return 1;

        int idx = 0; // Write pointer for compressed array output
        int i = 0;   // Read pointer to traverse character groups

        while (i < n) {
            char curr = chars[i];
            int count = 0;

            // Count contiguous occurrences of curr
            while (i < n && chars[i] == curr) {
                count++;
                i++; // Moves past the current group
            }

            // Write the character
            chars[idx++] = curr;

            // If count > 1, write each digit of count
            if (count > 1) {
                String num = String.valueOf(count);
                for (char digit : num.toCharArray()) {
                    chars[idx++] = digit;
                }
            }
            
            // Note: i is already pointing to the first character 
            // of the next group, so no i-- correction is needed!
        }

        return idx;
    }
}

class Solution1 {
    public int compress(char[] chars) {
        int n = chars.length;
        if (n == 1)
            return 1;

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