class Solution {
    public int numberOfWays(String corridor) {
        int n = corridor.length(), mod = 1000_000_007;
        if (n == 1)
            return 0;
        List<Integer> seatsIdx = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (corridor.charAt(i) == 'S')
                seatsIdx.add(i);
        }

        int ls = seatsIdx.size();
        if (ls == 0 || ls % 2 != 0)
            return 0;

        long ans = 1;
        for (int i = 2; i < ls; i += 2) {
            int prevSeat = seatsIdx.get(i - 1);
            int currSeat = seatsIdx.get(i);

            ans = (ans * (currSeat - prevSeat)) % mod;
        }
        return (int) ans;
    }
}