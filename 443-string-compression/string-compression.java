class Solution {
    public int compress(char[] chars) {
        int n = chars.length;
        if (n == 1)
            return 1;

        int idx = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            char curr = chars[i];
            while (i < n && curr == chars[i]) {
                count++;
                i++;
            }
            if (count == 1)
                chars[idx++] = curr;
            else {
                chars[idx++] = curr;
                String num = String.valueOf(count);
                for (char digit : num.toCharArray())
                    chars[idx++] = digit;
            }

            i--;
        }
        return idx;
    }
}