class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0)
            return false;

        Map<Integer, Integer> mp = new TreeMap<>();
        for (int num : hand)
            mp.put(num, mp.getOrDefault(num, 0) + 1);

        for (int i : mp.keySet()) {
            while (mp.get(i) > 0) {
                for (int j = 0; j < groupSize; j++) {
                    if (mp.getOrDefault(i + j, 0) <= 0)
                        return false;

                    mp.put(i + j, mp.get(i + j) - 1);
                }
            }
        }
        return true;
    }
}