class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Step 1: Remove duplicates from nums to shrink loop bounds
        int[] uniqueNums = Arrays.stream(nums).distinct().toArray();
        int m = uniqueNums.length;

        // Step 2: Compute all unique pair XORs
        boolean[] pairXors = new boolean[2048];
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                pairXors[uniqueNums[i] ^ uniqueNums[j]] = true;
            }
        }

        // Step 3: Combine unique pair XORs with single elements
        boolean[] tripletXors = new boolean[2048];
        for (int p = 0; p < 2048; p++) {
            if (!pairXors[p]) continue;
            for (int num : uniqueNums) {
                tripletXors[p ^ num] = true;
            }
        }

        // Step 4: Count unique triplet XOR results
        int count = 0;
        for (boolean present : tripletXors) {
            if (present) count++;
        }

        return count;
    }
}