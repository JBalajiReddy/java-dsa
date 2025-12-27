class Solution {
    class Pair {
        long end_time;
        int room_idx;

        Pair(long end_time, int room_idx) {
            this.end_time = end_time;
            this.room_idx = room_idx;
        }
    }

    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        PriorityQueue<Pair> busy = new PriorityQueue<>((a, b) -> {
            if (a.end_time != b.end_time) {
                return Long.compare(a.end_time, b.end_time);
            }
            return a.room_idx - b.room_idx;
        });

        PriorityQueue<Integer> avail_room = new PriorityQueue<>();
        int[] cnt = new int[n];

        for (int i = 0; i < n; i++) {
            avail_room.offer(i);
        }

        for (int[] m : meetings) {
            int start = m[0], end = m[1];
            int duration = end - start;
            // 1. CLEANUP PHASE
            while (!busy.isEmpty() && busy.peek().end_time <= start) {
                Pair meet = busy.poll();
                avail_room.offer(meet.room_idx);
            }

            // 2. BOOKING PHASE (Runs exactly once per meeting)
            if (!avail_room.isEmpty()) {
                // Case A: Room is available
                // TODO: Poll from avail, push to busy with (end, room_idx)
                int room = avail_room.poll();
                busy.offer(new Pair(end, room));
                cnt[room]++;
            } else {
                // Case B: Must wait (Delay)
                // TODO: Poll from busy, calc new_end = top.end_time + duration
                // Push back to busy with (new_end, top.room_idx)
                Pair meet = busy.poll();
                long new_end = meet.end_time + duration;
                int room = meet.room_idx;
                busy.offer(new Pair(new_end, room));
                cnt[room]++;
            }
        }

        int max_idx = 0, prev = cnt[0];
        for (int i = 1; i < n; i++) {
            if (cnt[i] > prev) {
                prev = cnt[i];
                max_idx = i;
            }
        }
        return max_idx;
    }
}