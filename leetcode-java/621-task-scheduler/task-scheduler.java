class Solution {
    public int leastInterval(char[] tasks, int n) {
        // Step 1: Count frequency of each task
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }

        // Step 2: Find the maximum frequency among all tasks
        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }

        // Step 3: Count how many tasks share this maximum frequency
        int countMax = 0;
        for (int f : freq) {
            if (f == maxFreq) {
                countMax++;
            }
        }

        // Step 4: Calculate minimal CPU slots required based on frame structure
        // (maxFreq - 1) gives full cooling chunks, each chunk of size (n + 1).
        // countMax accounts for the last trailing group of max-frequency tasks.
        int ans = (maxFreq - 1) * (n + 1) + countMax;

        // Step 5: Handle edge cases where total tasks exceeds calculated frames
        // Gotcha: If we have enough unique tasks to naturally fill all empty slots,
        // no IDLE time is needed, making the answer equal to the array length.
        return Math.max(tasks.length, ans);
    }
}


class Solution_PriorityQueue {
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