class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        
        // total_surplus: Tracks the net gas surplus/deficit across ALL stations combined.
        // surplus: Tracks the accumulated gas surplus for the CURRENT candidate trip.
        int total_surplus = 0;
        int surplus = 0;
        int start = 0; // Candidate starting station index
        
        for (int i = 0; i < n; i++) {
            int current_net = gas[i] - cost[i];
            
            total_surplus += current_net;
            surplus += current_net;
            
            // GREEDY RESET CONDITION:
            // If accumulated surplus drops below 0, it means starting from 'start'
            // up to station 'i' failed to reach station 'i + 1'.
            // None of the stations between 'start' and 'i' could possibly be a valid start either!
            if (surplus < 0) {
                surplus = 0;        // Reset tank for the next attempt
                start = i + 1;      // Greedily set the next candidate start station
            }
        }
        
        // MATHEMATICAL GUARANTEE:
        // If total_surplus >= 0, a valid circular route is mathematically guaranteed to exist,
        // and 'start' is that unique valid starting station. Otherwise, return -1.
        return (total_surplus < 0) ? -1 : start;
    }
}