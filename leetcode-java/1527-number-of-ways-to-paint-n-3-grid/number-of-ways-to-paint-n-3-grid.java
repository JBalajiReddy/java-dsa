class Solution {
    public int numOfWays(int n) {
        long mod = 1000_000_007;
        long aba = 6;
        long abc = 6;
        for (int i = 1; i < n; i++) {
            long prevABA = aba;
            long prevABC = abc;
            aba = (3 * prevABA + 2 * prevABC) % mod;
            abc = (2 * prevABA + 2 * prevABC) % mod;
        }
        return (int) ((aba + abc) % mod);
    }
}