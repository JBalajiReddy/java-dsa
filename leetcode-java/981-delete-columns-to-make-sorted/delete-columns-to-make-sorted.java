class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int res = 0;
        for (int c = 0; c < m; c++) {
            for (int r = 1; r < n; r++) {
                if (strs[r - 1].charAt(c) > strs[r].charAt(c)) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}