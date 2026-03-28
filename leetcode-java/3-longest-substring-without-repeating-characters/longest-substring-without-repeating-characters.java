class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len = 0; 
        int l = 0, r = 0; 
        int n = s.length();
        
        // Map to store the *most recent index* of each character we encounter
        Map<Character, Integer> mp = new HashMap<>(); 
        while (r < n) {
            char ch = s.charAt(r);
            
            // Have we seen this character before?
            if (mp.containsKey(ch)) {
                // instantly jump the left pointer 'l' to the right of the old duplicate.
                l = Math.max(l, mp.get(ch) + 1);
            }
            mp.put(ch, r);
            len = Math.max(len, r - l + 1);
            r++;
        }
        
        return len;
    }
}