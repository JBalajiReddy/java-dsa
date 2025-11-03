class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]); //descending order
        int totalUnits = 0;
        for (int[] box : boxTypes) {
            if (truckSize == 0)
                break;
            if (box[0] <= truckSize) {
                truckSize -= box[0];
                totalUnits += box[0] * box[1];
            } else {
                totalUnits += truckSize * box[1];
                truckSize = 0;
            }
        }
        return totalUnits;
    }
}