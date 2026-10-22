class Solution {
    public String reverseVowels(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        int l = 0, r = n - 1;
        while (l < r) {
            char chL = sb.charAt(l);
            char chR = sb.charAt(r);

            if (isVowel(chL) && isVowel(chR)) {
                sb.setCharAt(l, chR);
                sb.setCharAt(r, chL);
                l++;
                r--;
            } else if (!isVowel(chL)) {
                l++;
            } else if (!isVowel(chR)) {
                r--;
            } else {
                l++;
                r--;
            }
        }
        return sb.toString();
    }

    private boolean isVowel(char ch) {
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U');
    }
}