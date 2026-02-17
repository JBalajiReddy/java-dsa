class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        // Weights for the 10 LEDs: First 4 are hours, last 6 are minutes
        int[] weights = {8, 4, 2, 1, 32, 16, 8, 4, 2, 1};
        backtrack(weights, turnedOn, 0, 0, 0, ans);
        return ans;
    }

    private void backtrack(int[] weights, int count, int idx, int h, int m, List<String> ans) {
        // Pruning: If hours > 11 or minutes > 59, this path is invalid
        if (h > 11 || m > 59) return;

        // Base Case: If we have turned on exactly the required number of LEDs
        if (count == 0) {
            ans.add(h + ":" + (m < 10 ? "0" : "") + m);
            return;
        }

        // Iterate through the remaining LEDs starting from 'idx'
        for (int i = idx; i < weights.length; i++) {
            // Decision: Turn on the LED at index 'i'
            if (i < 4) { 
                // It's an Hour LED
                backtrack(weights, count - 1, i + 1, h + weights[i], m, ans);
            } else { 
                // It's a Minute LED
                backtrack(weights, count - 1, i + 1, h, m + weights[i], ans);
            }
        }
    }
}