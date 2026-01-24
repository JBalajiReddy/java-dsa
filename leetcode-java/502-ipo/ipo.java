class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        
        // 1. Store projects as {Capital, Profit} pairs in a 2D array
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }
        
        // 2. Sort by Capital (Ascending). 
        // This replaces the 'pqC' logic with a faster array sort.
        Arrays.sort(projects, (a, b) -> Integer.compare(a[0], b[0]));
        
        // 3. Max Heap for Profits (only stores Integers now)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        int ptr = 0;
        
        for (int i = 0; i < k; i++) {
            // Move all affordable projects to the heap
            // We use a pointer 'ptr' instead of polling from a queue
            while (ptr < n && projects[ptr][0] <= w) {
                pq.offer(projects[ptr][1]);
                ptr++;
            }
            
            // If we can't afford any new projects and have none in reserve
            if (pq.isEmpty()) {
                break;
            }
            
            // Take the best profit
            w += pq.poll();
        }
        
        return w;
    }
}

// class Solution {
//     class Pair {
//         int profit, capital;
//         Pair(int profit, int capital) {
//             this.profit = profit;
//             this.capital = capital;
//         }
//     }

//     public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
//         PriorityQueue<Pair> pqC = new PriorityQueue<>(
//                 (a, b) -> {
//                     return Integer.compare(a.capital, b.capital);
//                 });

//         PriorityQueue<Pair> pqP = new PriorityQueue<>(
//                 (a, b) -> {
//                     return Integer.compare(b.profit, a.profit);
//                 });

//         for (int i = 0; i < profits.length; i++) {
//             pqC.offer(new Pair(profits[i], capital[i]));
//         }

//         for (int i = 0; i < k; i++) {
//             while (!pqC.isEmpty() && pqC.peek().capital <= w) {
//                 pqP.offer(pqC.poll());
//             }
//             if (pqP.isEmpty()) break;
//             w += pqP.poll().profit;
//         }

//         return w;
//     }
// }