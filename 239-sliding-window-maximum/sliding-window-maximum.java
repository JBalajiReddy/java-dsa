//NGE - monotonic decreasing
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 1 || k == 1)
            return nums;
        Deque<Integer> dq = new ArrayDeque<>(); //store index
        int ri = 0;
        int[] res = new int[n - k + 1];

        for (int i = 0; i < n; i++) {

            //remove out-of-bound indices
            if (!dq.isEmpty() && dq.getFirst() <= i - k)
                dq.removeFirst();

            // remove smaller numbers in k range as they are useless
            while (!dq.isEmpty() && nums[dq.getLast()] <= nums[i])
                dq.removeLast();

            dq.addLast(i);

            if (i >= k - 1)
                res[ri++] = nums[dq.getFirst()];
        }

        return res;
    }
}

//first ------ last