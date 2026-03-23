class Solution {
    public String mergeAlternately(String word1, String word2) {
        int s1 = word1.length();
        int s2 = word2.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(s1, s2); i++) {
            char a = word1.charAt(i);
            char b = word2.charAt(i);
            sb.append(a);
            sb.append(b);
        }

        if (s1 > s2) {
            for (int i = s2; i < s1; i++) {
                sb.append(word1.charAt(i));
            }
        } else if (s2 > s1) {
            for (int i = s1; i < s2; i++) {
                sb.append(word2.charAt(i));
            }
        }
        return sb.toString();
    }
}