class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] cnt = new int[26];
        for (char t : tasks) {
            cnt[t - 'A']++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int freq : cnt) {
            if (freq > 0) {
                pq.offer(freq);
            }
        }

        int time = 0;
        Queue<int[]> q = new LinkedList<>();
        while (!pq.isEmpty() || !q.isEmpty()) {
            time++;
            if (pq.isEmpty()) {
                time = q.peek()[1];
            } else {
                int c = pq.poll() - 1;
                if (c > 0) {
                    q.offer(new int[] { c, time + n });
                }
            }

            if (!q.isEmpty() && q.peek()[1] == time) {
                pq.offer(q.poll()[0]);
            }
        }

        return time;
    }
}