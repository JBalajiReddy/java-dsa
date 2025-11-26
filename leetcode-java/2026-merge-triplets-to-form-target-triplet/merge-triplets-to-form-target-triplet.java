class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean fA, fB, fC;
        fA = fB = fC = false;

        for (int[] t : triplets) {
            if (t[0] > target[0] || t[1] > target[1] || t[2] > target[2]) continue;

            if (t[0] == target[0]) fA = true;
            if (t[1] == target[1]) fB = true;
            if (t[2] == target[2]) fC = true;

            if (fA && fB && fC) return true;
        }
        return fA && fB && fC;
    }
}