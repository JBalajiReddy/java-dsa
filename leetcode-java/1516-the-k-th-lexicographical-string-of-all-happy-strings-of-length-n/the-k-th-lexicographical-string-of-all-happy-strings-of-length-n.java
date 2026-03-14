class Solution {
    String res = "";
    int cnt = 0;

    public String getHappyString(int n, int k) {
        backtrack("", n, k);
        return res;
    }

    private void backtrack(String s, int n, int k) {
        if (s.length() == n) {
            cnt++;
            if (cnt == k) {
                res = s;
            }
            return;
        }

        for (char ch = 'a'; ch <= 'c'; ch++) {
            if (s.length() == 0 || s.charAt(s.length() - 1) != ch) {
                backtrack(s + ch, n, k);
            }
        }
    }
}