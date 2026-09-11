class Solution {
    public int totalNumbers(int[] digits) {
        // Step 1: Count frequency of each digit (0-9)
        int[] cnt = new int[10];
        for (int d : digits) {
            cnt[d]++;
        }

        Set<Integer> set = new HashSet<>();

        // Step 2: Form 3-digit numbers (i: hundreds, j: tens, k: units)
        // First digit cannot be 0 (must be 1 to 9)
        for (int i = 1; i <= 9; i++) {
            if (cnt[i] == 0)
                continue;
            cnt[i]--; // Choose hundreds digit

            // Second digit can be 0 to 9
            for (int j = 0; j <= 9; j++) {
                if (cnt[j] == 0)
                    continue;
                cnt[j]--; // Choose tens digit

                // Third digit must be even (0, 2, 4, 6, 8)
                for (int k = 0; k <= 8; k += 2) {
                    if (cnt[k] == 0)
                        continue;

                    // Construct the 3-digit even number
                    int num = i * 100 + j * 10 + k;
                    set.add(num);
                }

                cnt[j]++; // Backtrack tens digit
            }

            cnt[i]++; // Backtrack hundreds digit
        }

        return set.size();
    }
}