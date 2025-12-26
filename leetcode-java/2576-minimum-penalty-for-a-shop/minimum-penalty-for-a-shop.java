class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();
        int p = 0;
        for (char ch : customers.toCharArray()) {
            if (ch == 'Y') p++;
        }
        int idx = 0, min_p = p;
        for (int i = 1; i <= n; i++) {
            if (customers.charAt(i - 1) == 'Y') {
               p--;
            } else {
               p++;
            }

            if (p < min_p) {
                min_p = p;
                idx = i;
            }
        }
        return idx;
    }
}