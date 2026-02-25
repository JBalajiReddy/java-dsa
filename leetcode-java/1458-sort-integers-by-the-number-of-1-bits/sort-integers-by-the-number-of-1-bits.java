class Solution {
    class Pair {
        int cnt, num;
        Pair(int cnt, int num) {
            this.cnt = cnt;
            this.num = num;
        }
    }

    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if (a.cnt != b.cnt) {
                return Integer.compare(a.cnt, b.cnt);
            } else {
                return Integer.compare(a.num, b.num);
            }
        });

        for (int i = 0; i < n; i++) {
            int num = helper(arr[i]);
            pq.offer(new Pair(num, arr[i]));
        }

        int idx = 0;
        while (!pq.isEmpty()) {
            int cnt = pq.poll().num;
            res[idx++] = cnt;
        }
        return res;
    }

    private int helper(int n) {
        int cnt = 0;
        while (n > 0) {
            cnt += (n & 1);
            n >>= 1;
        }
        return cnt;
    }
}