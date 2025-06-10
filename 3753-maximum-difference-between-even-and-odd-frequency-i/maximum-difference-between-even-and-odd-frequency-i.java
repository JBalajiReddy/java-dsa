class Solution {
    public int maxDifference(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int maxOdd = -1, minEven = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int val = entry.getValue();
            if (val % 2 == 0) {
                if (minEven > val) {
                    minEven = val;
                }
            } else {
                if (maxOdd < val) {
                    maxOdd = val;
                }
            }
        }
        return maxOdd - minEven;
    }
}