class Solution {
    public int longestBalanced(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        
        // --- Part 1: Single Character Blocks ---
        // We track the current consecutive run length (cur_) and maximum seen (max_) 
        // for each character type independently.
        int cur_a = 0, cur_b = 0, cur_c = 0;        
        int max_a = 0, max_b = 0, max_c = 0;

        for (int i = 0; i < n; i++) {
            if (c[i] == 'a') {
                // If previous char was 'a', extend run; otherwise reset to 1
                cur_a = (i > 0 && c[i-1] == 'a') ? cur_a + 1 : 1;
                max_a = Math.max(max_a, cur_a);
            } else if (c[i] == 'b') {
                cur_b = (i > 0 && c[i-1] == 'b') ? cur_b + 1 : 1;
                max_b = Math.max(max_b, cur_b);
            } else { 
                cur_c = (i > 0 && c[i-1] == 'c') ? cur_c + 1 : 1;
                max_c = Math.max(max_c, cur_c);
            }
        }
        
        // Initial result is the max length of any single-character block
        int res = Math.max(Math.max(max_a, max_b), max_c);
        
        // --- Part 2: Two Character Balance ---
        // Check for substrings with equal counts of (x, y), ignoring the 3rd char.
        // We run this for all three combinations.
        res = Math.max(res, find2(c, 'a', 'b')); // Ignore 'c'
        res = Math.max(res, find2(c, 'a', 'c')); // Ignore 'b'
        res = Math.max(res, find2(c, 'b', 'c')); // Ignore 'a'
        
        // --- Part 3: Three Character Balance ---
        // Check for substrings where count(a) == count(b) == count(c)
        res = Math.max(res, find3(c));
        
        return res;
    }
    
    /**
     * Helper to find longest substring with equal counts of char x and y.
     * The third character acts as a "reset" or barrier.
     */
    private int find2(char[] c, char x, char y) {
        int n = c.length, max_len = 0;
        
        // 'first' stores the first index where a specific difference (count_x - count_y) was seen.
        // Size is 2*n+1 because the difference can range from -n to +n.
        int[] first = new int[2 * n + 1];
        Arrays.fill(first, -2); // Initialize with -2 (invalid index)
        
        // clear_idx tracks the index of the last "forbidden" character (the 3rd type).
        // Any valid substring must start AFTER this index.
        int clear_idx = -1;
        
        // 'diff' represents the running balance: count(x) - count(y).
        // We offset by 'n' to keep array indices positive.
        int diff = n;
        first[diff] = -1; // Base case: difference of 0 happens at virtual index -1
        
        for (int i = 0; i < n; i++) {
            // If character is neither x nor y, it breaks the continuity.
            if (c[i] != x && c[i] != y) {
                clear_idx = i; // Mark the barrier
                diff = n;      // Reset difference to 0 (offset by n)
                first[diff] = i; // The "new" 0 difference starts here
            } else {
                // Update balance: +1 for x, -1 for y
                if (c[i] == x) diff++;
                else diff--;
                
                // If first[diff] < clear_idx, it means the entry in 'first' is stale 
                // (it occurred before the last reset/barrier). Overwrite it.
                if (first[diff] < clear_idx) {
                    first[diff] = i;
                } else {
                    // We found the same difference after the barrier.
                    // The substring between first[diff] and i has equal numbers of x and y.
                    max_len = Math.max(max_len, i - first[diff]);
                }
            }
        }
        
        return max_len;
    }
    
    /**
     * Helper to find longest substring where count(a) == count(b) == count(c).
     * Uses Vector Hashing to track multiple differences simultaneously.
     */
    private int find3(char[] c) {
        // 'state' encodes the relative differences into a single number.
        // Start in the middle of Long range to prevent underflow/overflow.
        long state = Long.MAX_VALUE / 2;
        
        // Map stores: Encoded State -> First Index it appeared
        Map<Long, Integer> first = new HashMap<>();
        first.put(state, -1);
    
        int max_len = 0;
    
        for (int i = 0; i < c.length; i++) {
            // We use a base-1,000,000 system to simulate two separate counters in one Long.
            // "Millions" place tracks: count(a) - count(b)
            // "Ones" place tracks:     count(a) - count(c)
            
            if (c[i] == 'a') {
                // 'a' contributes +1 to both differences
                state += 1_000_001; 
            } else if (c[i] == 'b') {
                // 'b' reduces (count(a)-count(b)) but doesn't affect (count(a)-count(c))
                state -= 1_000_000;
            } else {
                // 'c' reduces (count(a)-count(c)) but doesn't affect (count(a)-count(b))
                state--;
            }
        
            // If we encounter the same 'state' again, it means the net change 
            // for both (A vs B) and (A vs C) is zero since the first occurrence.
            if (first.containsKey(state)) {
                max_len = Math.max(max_len, i - first.get(state));
            } else {
                first.put(state, i);
            }
        }
    
        return max_len;
    }
}