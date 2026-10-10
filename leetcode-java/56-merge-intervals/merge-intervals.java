class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n <= 1) {
            return intervals;
        }

        // 1. SORT BY START TIME
        // Sorting ensures overlapping or adjacent intervals sit next to each other.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // 2. REFERENCE METHOD EXPLANATION:
        // In Java, arrays are objects stored on the heap.
        // 'prev' holds a MEMORY REFERENCE (pointer) to 'intervals[0]'.
        // When we add 'prev' to 'ls', the list stores that exact same reference.
        int[] prev = intervals[0];
        List<int[]> ls = new ArrayList<>();
        ls.add(prev);

        for (int i = 1; i < n; i++) {
            int[] curr = intervals[i];

            // 3. CHECK OVERLAP
            if (prev[1] >= curr[0]) {
                // IN-PLACE MUTATION VIA REFERENCE:
                // Modifying 'prev[1]' directly updates the object in heap memory.
                // Since 'ls.get(0)' points to the same object, the list's contents
                // update automatically without needing a pop() or set() operation!
                prev[1] = Math.max(prev[1], curr[1]);
            } else {
                // NO OVERLAP:
                // Add the new interval reference to the list, then update 'prev' 
                // to point to 'curr' for future iterations.
                ls.add(curr);
                prev = curr;
            }
        }

        // Standard shortcut to convert List<int[]> back to int[][]
        return ls.toArray(new int[ls.size()][]);

        /* 
        // ALTERNATIVE: Explicit 2D Array Conversion
        // If 'ls.toArray(new int[ls.size()][])' feels tricky to remember during an OA,
        // you can explicitly allocate and copy elements like this:
        
        // Create the destination 2D array explicitly
        int[][] result = new int[ls.size()][2];
        
        // Copy from list into the array
        for (int i = 0; i < ls.size(); i++) {
            result[i] = ls.get(i);
        }
        
        return result;
        */
    }
}

class Solution1 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // 1. Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> result = new ArrayList<>();

        // Add the first interval explicitly to start our result list
        result.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];

            // Get a snapshot of the last merged interval added to our result list
            int[] lastMerged = result.get(result.size() - 1);

            // Check if current overlaps with lastMerged
            if (current[0] <= lastMerged[1]) {
                // OVERLAP: Create a brand new merged interval array
                int newStart = lastMerged[0];
                int newEnd = Math.max(lastMerged[1], current[1]);

                // Replace the old tail of the list with our freshly built array
                result.set(result.size() - 1, new int[] { newStart, newEnd });
            } else {
                // NO OVERLAP: Add current interval as a new standalone block
                result.add(current);
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}