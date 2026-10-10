class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n <= 1) return n;

        // 1. Create an array of indices to sort cars by position without losing speed mapping
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Sort indices by car starting position ASCENDING
        Arrays.sort(indices, (a, b) -> Integer.compare(position[a], position[b]));

        int fleet = 1;
        
        // Use double to prevent integer division truncation!
        // Calculate initial lead car's time to target
        int leadIdx = indices[n - 1];
        double prevTime = (double) (target - position[leadIdx]) / speed[leadIdx];

        // Process remaining cars from right to left (closest to target first)
        for (int i = n - 2; i >= 0; i--) {
            int idx = indices[i];
            double time = (double) (target - position[idx]) / speed[idx];

            // If a car behind takes STRICTLY MORE time, it can never catch up -> NEW FLEET!
            if (time > prevTime) {
                fleet++;
                prevTime = time; // Update lead fleet time baseline
            }
        }

        return fleet;
    }
}