class Solution {
    public String processStr(String s) {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                if (sb.length() >= 1) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else if (ch == '#') {
                StringBuilder sb1 = sb;
                sb.append(sb1);
            } else if (ch == '%') {
                sb = sb.reverse();
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}