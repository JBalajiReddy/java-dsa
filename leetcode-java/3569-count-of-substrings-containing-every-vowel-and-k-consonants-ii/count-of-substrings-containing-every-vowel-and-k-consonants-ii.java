class Solution {
    public long countOfSubstrings(String word, int k) {
        return atLeastK(word, k) - atLeastK(word, k + 1);
    }

    public long atLeastK(String s, int k) {
        long numValidSubStrings = 0;
        HashMap<Character, Integer> vowelC = new HashMap<>();
        int consonantC = 0;
        int start = 0;
        int end = 0;
        // start sliding window
        while (end < s.length()) {
            Character ch = s.charAt(end);
            if (isVowel(ch)) {
                vowelC.put(ch, vowelC.getOrDefault(ch, 0) + 1);
            } else {
                consonantC++;
            }
            // shrink window while we have a valid substring
            while (vowelC.size() == 5 && consonantC >= k) {
                numValidSubStrings += (s.length() - end);
                Character startCh = s.charAt(start);
                if (isVowel(startCh)) {
                    vowelC.put(startCh, vowelC.getOrDefault(startCh, 0) - 1);
                    if (vowelC.get(startCh) == 0) {
                        vowelC.remove(startCh);
                    }
                } else {
                    consonantC--;
                }
                start++;
            }
            end++;
        }
        return numValidSubStrings;
    }

    public boolean isVowel(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }
}