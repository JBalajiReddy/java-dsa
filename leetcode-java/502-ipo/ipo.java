class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = capital.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> Integer.compare(capital[a], capital[b]));

        PriorityQueue<Integer> maxProfit = new PriorityQueue<>(Collections.reverseOrder());
        int idx = 0;

        for (int i = 0; i < k; i++) {
            while (idx < n && capital[indices[idx]] <= w) {
                maxProfit.offer(profits[indices[idx]]);
                idx++;
            }

            if (maxProfit.isEmpty()) {
                break;
            }

            w += maxProfit.poll();
        }

        return w;
    }
}