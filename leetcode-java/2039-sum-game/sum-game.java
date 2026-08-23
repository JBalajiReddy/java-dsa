class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        // Step 1: Parse sums and '?' counts for both halves
        int[] left = get(num.substring(0, n / 2));
        int[] right = get(num.substring(n / 2, n));

        int n0 = left[0],  q0 = left[1];  // Left sum, Left '?' count
        int n1 = right[0], q1 = right[1]; // Right sum, Right '?' count

        // Condition 1: Odd number of total '?' -> Alice always gets the last turn and wins.
        // Condition 2: Check if the net sum difference CANNOT be compensated by Bob's pair strategy.
        return (q0 + q1) % 2 == 1 || (n0 - n1) != ((q1 - q0) * 9) / 2;
    }

    private int[] get(String s) {
        int nn = 0, qq = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '?') {
                qq++;
            } else {
                nn += ch - '0';
            }
        }
        return new int[] { nn, qq };
    }
}