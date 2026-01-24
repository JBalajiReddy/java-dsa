class Solution {
    class Pair {
        int profit, capital;
        Pair(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Pair> pqC = new PriorityQueue<>(
                (a, b) -> {
                    return Integer.compare(a.capital, b.capital);
                });

        PriorityQueue<Pair> pqP = new PriorityQueue<>(
                (a, b) -> {
                    return Integer.compare(b.profit, a.profit);
                });

        for (int i = 0; i < profits.length; i++) {
            pqC.offer(new Pair(profits[i], capital[i]));
        }

        for (int i = 0; i < k; i++) {
            while (!pqC.isEmpty() && pqC.peek().capital <= w) {
                pqP.offer(pqC.poll());
            }
            if (pqP.isEmpty()) break;
            w += pqP.poll().profit;
        }

        return w;
    }
}