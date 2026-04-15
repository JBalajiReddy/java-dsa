class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;

        // 'step' represents the distance moving outward from startIndex
        for (int step = 0; step < n; step++) {
            
            // Calculate the pointers moving right and left
            int right = (startIndex + step) % n;
            int left = (startIndex - step + n) % n;
            
            // If either pointer hits the target, we return the step count immediately.
            // Because we expand outwards step-by-step, the first match is guaranteed 
            // to be the absolute shortest distance.
            if (words[right].equals(target) || words[left].equals(target)) {
                return step;
            }
        }

        // If we expand through the whole array and never return, it doesn't exist
        return -1;
    }
}



// class Solution {
//     public int closestTarget(String[] words, String target, int startIndex) {
//         int n = words.length;
//         int minDistance = Integer.MAX_VALUE;

//         for (int i = 0; i < n; i++) {
//             if (words[i].equals(target)) {
//                 // Calculate the distance without wrapping around
//                 int directDistance = Math.abs(i - startIndex);
                
//                 // Calculate the distance by wrapping around the circular array
//                 int wrapDistance = n - directDistance;
                
//                 // The shortest path to this specific target
//                 int shortestPath = Math.min(directDistance, wrapDistance);
                
//                 // Update the global minimum distance
//                 minDistance = Math.min(minDistance, shortestPath);
//             }
//         }

//         // If minDistance was never updated, the target wasn't found
//         return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
//     }
// }