class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] cnt = new int[26];
        for (char t : tasks) {
            cnt[t - 'A']++;
        }

        //Max-Heap to always pick the task with the highest remaining frequency
        // This greedy strategy maximizes the diversity of tasks left for future cooling slots
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int freq : cnt) {
            if (freq > 0) {
                pq.offer(freq);
            }
        }

        int time = 0;
        // CoolDown Queue stores waiting tasks as pairs: [remaining_count, available_time]
        Queue<int[]> q = new LinkedList<>();

        while (!pq.isEmpty() || !q.isEmpty()) {
            time++;

            if (pq.isEmpty()) {
                // Optimization: Fast-forward time to when the earliest waiting task becomes available
                // Prevents unnecessary 1-by-1 idle loop increments
                time = q.peek()[1];
            } else {
                // Execute the task with the highest remaining count
                int c = pq.poll() - 1;
                
                // If the task still has remaining executions, put it in the cooling queue
                // It will be eligible to run again at (current_time + cooling_period)
                if (c > 0) {
                    q.offer(new int[] { c, time + n });
                }
            }

            // Check if any cooling task has completed its waiting period
            if (!q.isEmpty() && q.peek()[1] == time) {
                // Move the task back into the max-heap to make it available for execution
                pq.offer(q.poll()[0]);
            }
        }

        return time;
    }
}