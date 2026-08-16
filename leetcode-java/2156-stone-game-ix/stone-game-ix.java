class Solution {
    public boolean stoneGameIX(int[] stones) {
        int cnt0 = 0, cnt1 = 0, cnt2 = 0;
        
        // Step 1: Count frequencies of remainders modulo 3
        for (int val : stones) {
            int type = val % 3;
            if (type == 0) ++cnt0;
            else if (type == 1) ++cnt1;
            else ++cnt2;
        }
        
        // Step 2: Evaluate game outcome based on zero-stone parity
        if (cnt0 % 2 == 0) {
            // Even 0-stones: Alice wins if both remainder types are available
            return cnt1 >= 1 && cnt2 >= 1;
        }
        
        // Odd 0-stones: Alice wins if the gap between count of 1s and 2s is > 2
        return cnt1 - cnt2 > 2 || cnt2 - cnt1 > 2;
    }
}