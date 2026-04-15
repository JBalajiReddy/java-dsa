class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                // Calculate the distance without wrapping around
                int directDistance = Math.abs(i - startIndex);
                
                // Calculate the distance by wrapping around the circular array
                int wrapDistance = n - directDistance;
                
                // The shortest path to this specific target
                int shortestPath = Math.min(directDistance, wrapDistance);
                
                // Update the global minimum distance
                minDistance = Math.min(minDistance, shortestPath);
            }
        }

        // If minDistance was never updated, the target wasn't found
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}