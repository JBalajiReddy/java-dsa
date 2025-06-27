class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 1 || k == 1)
            return nums;
        Deque<Integer> dq = new ArrayDeque<>();
        int[] res = new int[n - k + 1];
        int idx = 0;

        for (int i = 0; i < n; i++) {
            // Remove out-of-bound indices
            if (!dq.isEmpty() && dq.getFirst() <= i - k) {
                dq.removeFirst();
            }

            // Maintain descending order in deque
            while (!dq.isEmpty() && nums[dq.getLast()] < nums[i]) {
                dq.removeLast();
            }

            dq.addLast(i);

            // Start adding to result once the first window is hit
            if (i >= k - 1) {
                res[idx++] = nums[dq.getFirst()];
            }
        }

        return res;
    }
}


// class Solution {
//     public int[] maxSlidingWindow(int[] nums, int k) {
//         List<Integer> result = new ArrayList<>();
//         int n = nums.length;

//         for (int i = 0; i <= n - k; i++) {
//             int max = nums[i];
//             for (int j = i + 1; j < i + k; j++) {
//                 max = Math.max(max, nums[j]);
//             }
//             result.add(max);
//         }

//         int[] res = new int[result.size()];
//         int idx = 0;
//         for (int i : result)
//             res[idx++] = i;
//         return res;
//     }
// }