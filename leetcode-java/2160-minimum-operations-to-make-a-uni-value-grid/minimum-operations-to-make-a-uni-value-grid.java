class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> ls = new ArrayList<>();
        
        // Step 1: Flatten the 2D grid into a 1D list
        for (int[] row : grid) {
            for (int colEle : row) {
                ls.add(colEle);
            }
        }

        // Step 2: Sort the elements to easily extract the median
        Collections.sort(ls);
        int n = ls.size();

        // Step 3: Choose the median as the target value.
        // The median minimizes the sum of absolute differences (|a_i - T|).
        int target = ls.get(n / 2); 

        int ops = 0;
        
        // Step 4: Validate feasibility and accumulate operations
        for (int num : ls) {
            // All numbers must leave the same remainder when divided by x.
            // If remainder differs, num cannot reach target by adding/subtracting x.
            if (target % x != num % x) {
                return -1;
            }

            // Calculate the exact number of steps (+x or -x) needed to reach target
            ops += Math.abs(target - num) / x;
        }
        
        return ops;
    }
}