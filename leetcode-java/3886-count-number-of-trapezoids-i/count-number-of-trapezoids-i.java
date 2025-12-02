class Solution {
    int MOD = 1000000007;
    public int countTrapezoids(int[][] points) {
        Map<Integer, Long> map = new HashMap<>();
        for (int point[] : points) {
            int y = point[1];
            map.put(y, map.getOrDefault(y, 0L) + 1);
        }
        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            long val = entry.getValue();
            long newVal = ((val * (val - 1)) / 2) % MOD;
            entry.setValue(newVal);
        }
        long ans = 0;
        long sum = 0;
        for (long val : map.values()) {
            ans = (ans + (sum * val) % MOD) % MOD;
            sum = (sum + val) % MOD;
        }
        return (int) ans;
    }
}