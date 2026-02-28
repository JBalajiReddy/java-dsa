class Solution {
    public int concatenatedBinary(int n) {
        int mod = 1000_000_007;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(Integer.toBinaryString(i));
        }
        long num = 0;
        for (int i = 0; i < sb.length(); i++) {
            num = (num * 2 + sb.charAt(i) - '0') % mod;
        }
        return (int) num;
    }
}