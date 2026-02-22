class Solution {
    public int binaryGap(int n) {
        int dist = 0;
        ArrayList<Integer> ls = new ArrayList<>();
        while (n > 0) {
            int bit = (n & 1);
            ls.add(bit);
            n = n >> 1;
        }
        for (int i = 0; i < ls.size() - 1; i++) {
            int cur = 1;
            if (ls.get(i) == 1) {
                while (ls.get(i + 1) == 0 && i + 1 < ls.size()) {
                    cur++;
                    i++;
                }
                dist = Math.max(dist, cur);
            }
        }
        return dist;
    }
}