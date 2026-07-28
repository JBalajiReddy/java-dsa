class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        char[] ch = new char[n];
        for (int i = 0; i < n; i++) {
            ch[i] = s.charAt(i);
        }

        Arrays.sort(ch, 0, n / 2);

        for (int i = 0; i < n / 2; i++) {
            ch[n - 1 - i] = ch[i];
        }

        return new String(ch);
    }
}