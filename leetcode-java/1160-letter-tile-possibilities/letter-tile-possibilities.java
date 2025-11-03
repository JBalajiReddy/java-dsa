class Solution {
    public int numTilePossibilities(String tiles) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : tiles.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        return backtrack(freq);
    }

    public int backtrack(Map<Character, Integer> freq) {
        int count = 0;
        for (char ch : freq.keySet()) {
            if (freq.get(ch) > 0) {
                count++;
                freq.put(ch, freq.getOrDefault(ch, 0) - 1);
                count += backtrack(freq);
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            }
        }
        return count;
    }
}