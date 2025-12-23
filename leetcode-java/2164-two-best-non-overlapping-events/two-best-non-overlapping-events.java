class Solution {
    int n;
    Integer[][] dp;
    public int maxTwoEvents(int[][] events) {
        n = events.length;
        dp = new Integer[n][3];
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        return solve(events, 0, 0);
    }

    private int solve(int[][] events, int idx, int cnt) {
        if (idx == n || cnt == 2) {
            return 0;
        }

        if (dp[idx][cnt] != null) {
            return dp[idx][cnt];
        }

        int exclude = solve(events, idx + 1, cnt);
        int nextIdx = findNextIdx(events, idx);
        int include = events[idx][2] + solve(events, nextIdx, cnt + 1);

        return dp[idx][cnt] = Math.max(include, exclude);
    }

    private int findNextIdx(int[][] e, int idx) {
        int t = e[idx][1];
        int low = idx + 1, high = n - 1, ans = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (e[mid][0] > t) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
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