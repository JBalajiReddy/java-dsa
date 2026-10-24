class Solution {
    public int countDaysTogether(
            String arriveAlice,
            String leaveAlice,
            String arriveBob,
            String leaveBob) {

        // Days in each month for a non-leap year (January to December)
        int[] datesOfMonths = {
                31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };

        // prefixSum[i] stores the total days elapsed prior to month i (1-indexed)
        // prefixSum[0] = 0 (before Jan)
        // prefixSum[1] = 31 (before Feb)
        // prefixSum[2] = 59 (before Mar), etc.
        int[] prefixSum = new int[13];
        for (int i = 0; i < 12; i++) {
            prefixSum[i + 1] = prefixSum[i] + datesOfMonths[i];
        }

        // Convert string dates ("MM-DD") to absolute day numbers of the year (1 to 365)
        int arriveAliceDay = calculateDayOfYear(arriveAlice, prefixSum);
        int leaveAliceDay = calculateDayOfYear(leaveAlice, prefixSum);
        int arriveBobDay = calculateDayOfYear(arriveBob, prefixSum);
        int leaveBobDay = calculateDayOfYear(leaveBob, prefixSum);

        // Find intersection of intervals [arriveAliceDay, leaveAliceDay] and [arriveBobDay, leaveBobDay]
        // Overlap starts at latest arrival date: Math.max(arriveAliceDay, arriveBobDay)
        // Overlap ends at earliest departure date: Math.min(leaveAliceDay, leaveBobDay)
        int overlapStart = Math.max(arriveAliceDay, arriveBobDay);
        int overlapEnd = Math.min(leaveAliceDay, leaveBobDay);

        // Compute inclusive days between overlapStart and overlapEnd.
        // Return 0 if there is no valid overlap (i.e., overlapEnd < overlapStart).
        return Math.max(0, overlapEnd - overlapStart + 1);
    }

    // Helper method to convert a date string "MM-DD" into an absolute day of the year
    private int calculateDayOfYear(String day, int[] prefixSum) {
        // Extract 1-based month (chars 0-1) and date (chars 3-4)
        int month = Integer.parseInt(day.substring(0, 2));
        int date = Integer.parseInt(day.substring(3));

        // Total days = (days elapsed in preceding months) + (current day of the month)
        return prefixSum[month - 1] + date;
    }
}