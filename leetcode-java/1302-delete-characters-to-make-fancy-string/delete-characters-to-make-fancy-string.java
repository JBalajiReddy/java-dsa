class Solution {
    public String makeFancyString(String s) {
        if (s.length() < 3) return s;
        StringBuilder sb = new StringBuilder();
        char[] ch = s.toCharArray();
        sb.append(ch[0]);
        sb.append(ch[1]);
        for (int i = 2; i < ch.length; i++) {
            if (ch[i - 2] == ch[i - 1] && ch[i - 1] == ch[i])
                continue;
            sb.append(ch[i]);
        }
        return sb.toString();
    }
}