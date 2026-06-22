class Solution {
    public int maxNumberOfBalloons(String text) {
        Map<Character, Integer> mp = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == 'b' || ch == 'a' || ch == 'l' || ch == 'o' || ch == 'n') {
                mp.put(ch, mp.getOrDefault(ch, 0) + 1);
            }
        }

        int b = 0, a = 0, l = 0, o = 0, n = 0;
        
        for (Map.Entry<Character, Integer> entry : mp.entrySet()) {
            char ch = entry.getKey();
            int freq = entry.getValue();
            if (ch == 'b') {
                b = freq;
            } else if (ch == 'a') {
                a = freq;
            } else if (ch == 'l') {
                l = freq;
            } else if (ch == 'o') {
                o = freq;
            } else if (ch == 'n') {
                n = freq;
            }
        }

        // 'l' and 'o' occur twice in "balloon", so integer divide their counts by 2
        l = l / 2;
        o = o / 2;

        // The bottleneck character determines the maximum number of words we can form
        return Math.min(b, Math.min(a, Math.min(l, Math.min(o, n))));
    }
}