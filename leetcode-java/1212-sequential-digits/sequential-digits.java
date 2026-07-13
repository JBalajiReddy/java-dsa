// class Solution {
//     public List<Integer> sequentialDigits(int low, int high) {
//         String s = "123456789";
//         List<Integer> ls = new ArrayList<>();
//         int minLen = String.valueOf(low).length();
//         int maxLen = String.valueOf(high).length();
//         for (int len = minLen; len <= maxLen; len++) {
//             for (int start = 0; start <= 9 - len; start++) {
//                 int num = Integer.parseInt(s.substring(start, start + len));
//                 if (num >= low && num <= high) {
//                     ls.add(num);
//                 }
//             }
//         }
//         return ls;
//     }
// }

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        
        // Seed the queue with single-digit starting numbers 1 through 8
        // (9 cannot transition to a next digit since 9+1 = 10)
        for (int i = 1; i <= 8; i++) {
            queue.add(i);
        }
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            // Extract the last digit to find the next valid sequential digit
            int lastDigit = current % 10;
            
            // If the last digit is 9, we cannot append anything next (e.g., 89 cannot become 890)
            if (lastDigit < 9) {
                // Construct the next sequential number
                int nextNum = current * 10 + (lastDigit + 1);
                
                // Collect it if it falls inside our target window
                if (nextNum >= low && nextNum <= high) {
                    result.add(nextNum);
                }
                
                // Keep exploring deeper if the number hasn't exceeded the upper bound
                if (nextNum < high) {
                    queue.add(nextNum);
                }
            }
        }
        
        // Sorting is required because BFS processes numbers by digit length, 
        // which naturally sorts by length, but out of order for strict numerical values
        // (e.g., 12, 23... then 123, 234). Since they are generated in length-order, 
        // the final list is automatically sorted cleanly!
        return result;
    }
}