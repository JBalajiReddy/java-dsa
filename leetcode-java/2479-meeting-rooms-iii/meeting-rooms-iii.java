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
        // Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        // Busy rooms: Min-Heap sorted by end_time (primary) and room_idx (secondary)
        PriorityQueue<Pair> busy = new PriorityQueue<>((a, b) -> {
            if (a.end_time != b.end_time) {
                return Long.compare(a.end_time, b.end_time);
            }
            return Integer.compare(a.room_idx, b.room_idx);
        });

        // Available rooms: Min-Heap sorted by room_idx (lowest index first)
        PriorityQueue<Integer> avail_room = new PriorityQueue<>();

        // Initialize all rooms as available
        for (int i = 0; i < n; i++) {
            avail_room.offer(i);
        }

        int[] cnt = new int[n];

        for (int[] m : meetings) {
            int start = m[0];
            int end = m[1];
            // Duration is constant regardless of delay
            long duration = end - start;

            // 1. CLEANUP PHASE: Free up rooms that finished before current meeting starts
            while (!busy.isEmpty() && busy.peek().end_time <= start) {
                Pair meet = busy.poll();
                avail_room.offer(meet.room_idx);
            }

            // 2. BOOKING PHASE
            if (!avail_room.isEmpty()) {
                // Case A: Room is available -> No delay
                int room = avail_room.poll();
                busy.offer(new Pair(end, room));
                cnt[room]++;
            } else {
                // Case B: No room available -> Must delay
                // Pick the room finishing earliest
                Pair meet = busy.poll();
                long new_end = meet.end_time + duration;
                int room = meet.room_idx;

                // Push back directly to busy with updated end time
                busy.offer(new Pair(new_end, room));
                cnt[room]++;
            }
        }

        int max_idx = 0;
        for (int i = 1; i < n; i++) {
            if (cnt[i] > cnt[max_idx]) {
                max_idx = i;
            }
        }
        return max_idx;
    }
}