class Solution {
    int n; 
    Integer[][] dp;

    public int maxTwoEvents(int[][] events) {
        n = events.length;
        // dp[i][j] stores the max value starting from event index 'i' with 'j' events already picked
        dp = new Integer[n][3]; 
        
        // 1. Sort events by start time.
        // This is crucial so we can efficiently find the next non-overlapping event using Binary Search.
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Start recursion from the first event (index 0) with 0 events picked so far.
        return solve(events, 0, 0);
    }

    // Recursive function to solve the problem
    // idx: current event index we are considering
    // cnt: number of events we have already picked (0, 1, or 2)
    private int solve(int[][] events, int idx, int cnt) {
        // Base Case: Stop if we've checked all events OR if we've already picked 2 events.
        if (idx == n || cnt == 2) {
            return 0;
        }

        // Memoization Check: If we already computed this state, return the stored value.
        if (dp[idx][cnt] != null) {
            return dp[idx][cnt];
        }

        // Option A: EXCLUDE the current event
        // We simply move to the next index without increasing the count or adding value.
        int exclude = solve(events, idx + 1, cnt);

        // Option B: INCLUDE the current event
        // 1. We gain the value of the current event: events[idx][2]
        // 2. We must find the next valid event that starts AFTER this one ends (no overlap).
        int nextIdx = findNextIdx(events, idx);
        int include = events[idx][2] + solve(events, nextIdx, cnt + 1);

        // Store the best choice (Max of Include vs Exclude) in the DP table and return it.
        return dp[idx][cnt] = Math.max(include, exclude);
    }

    // Binary Search to find the nearest next event that does not overlap
    // idx: the index of the current event we just picked
    private int findNextIdx(int[][] e, int idx) {
        int t = e[idx][1]; // 't' is the END time of the current event
        int low = idx + 1, high = n - 1, ans = n;

        // Standard Binary Search to find the first event where start_time > current_end_time
        while (low <= high) {
            int mid = (low + high) / 2;
            
            // Check if event at 'mid' starts strictly after our current event ends
            if (e[mid][0] > t) {
                ans = mid;      // This is a valid next event, but there might be an earlier one to the left.
                high = mid - 1; // Try to find a closer valid event (move left).
            } else {
                low = mid + 1;  // This event overlaps (starts too early), so we must look further right.
            }
        }
        return ans; // Returns 'n' if no valid next event is found
    }
}

// class Solution {
//     public int maxTwoEvents(int[][] events) {
//         PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
//         Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
//         int maxPrevVal = 0, maxSum = 0;
//         for (int[] e : events) {
//             while (!pq.isEmpty() && pq.peek()[1] < e[0]) {
//                 maxPrevVal = Math.max(maxPrevVal, pq.peek()[2]);
//                 pq.poll();
//             }
//             maxSum = Math.max(maxSum, maxPrevVal + e[2]);
//             pq.offer(e);
//         }
//         return maxSum;
//     }
// }