class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];
        
        // Map to store the list of indices for each number
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // Process each unique number's list of indices
        for (List<Integer> indices : mp.values()) {
            int size = indices.size();
            long totalSum = 0;
            
            // Calculate the sum of all indices for this specific number
            for (int idx : indices) {
                totalSum += idx;
            }

            long leftSum = 0;
            long rightSum = totalSum;

            for (int i = 0; i < size; i++) {
                int currentIndex = indices.get(i);
                
                // Remove the current index from the right side calculation
                rightSum -= currentIndex; 

                long leftCount = i;
                long rightCount = size - 1 - i;

                // Apply the prefix sum distance formulas
                long leftDistance = (leftCount * currentIndex) - leftSum;
                long rightDistance = rightSum - (rightCount * currentIndex);

                // Store the result
                res[currentIndex] = leftDistance + rightDistance;

                // Add the current index to the left side for the next iteration
                leftSum += currentIndex; 
            }
        }
        
        return res;
    }
}