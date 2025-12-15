class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> Integer.compare(position[a], position[b]));

        int fleets = 0;
        double bottleneck = 0;
        for (int i = n - 1; i >= 0; i--) {
            int idx = indices[i];
            int pos = position[idx];
            int spd = speed[idx];

            double time = (double) (target - pos) / spd;

            //car cannot catch up ahead car
            if (time > bottleneck) {
                fleets++;
                bottleneck = time;
            }
        }
        return fleets;
    }
}