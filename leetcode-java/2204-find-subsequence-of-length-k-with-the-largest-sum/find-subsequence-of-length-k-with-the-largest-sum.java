class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        // Min-Heap ordered by value: (value, index)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Step 1: Maintain the top k largest elements using a Min-Heap
        for (int i = 0; i < nums.length; i++) {
            minHeap.offer(new int[]{nums[i], i});
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove smallest element
            }
        }

        // Step 2: Extract the k elements and sort them by original index
        int[][] kLargest = new int[k][2];
        int idx = 0;
        while (!minHeap.isEmpty()) {
            kLargest[idx++] = minHeap.poll();
        }

        // Sort by index (element[1]) to restore original relative order
        Arrays.sort(kLargest, (a, b) -> Integer.compare(a[1], b[1]));

        // Step 3: Build output array containing the values
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = kLargest[i][0];
        }

        return result;
    }
}