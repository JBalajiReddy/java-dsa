class Solution {
    public int numberOfBeams(String[] bank) {
        int res = 0, prev = 0;
        for (String s : bank) {
            int cnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    cnt++;
                }
            }
            if (cnt > 0) {
                res += cnt * prev;
                prev = cnt;
            }
        }
        return res;
    }
}