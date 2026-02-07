class Solution {
    public int minimumDeletions(String s) {
        int minDel = 0, b = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'b') {
                b++;
            } else {
                minDel = Math.min(minDel + 1, b);
            }
        }
        return minDel;
    }
}