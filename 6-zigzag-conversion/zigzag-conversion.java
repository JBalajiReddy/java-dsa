class Solution {
    public String convert(String s, int numRows) {
        String[] rows = new String[numRows];
        for (int i = 0; i < numRows; i++)
            rows[i] = "";

        int i = 0;
        while (i < s.length()) {
            // Fill the rows from top to bottom
            for (int idx = 0; idx < numRows && i < s.length(); idx++)
                rows[idx] += s.charAt(i++);

            // Fill the rows from bottom to top
            for (int idx = numRows - 2; idx > 0 && i < s.length(); idx--)
                rows[idx] += s.charAt(i++);
        }
        String res = "";
        for (String str : rows)
            res += str;

        return res;
    }
}