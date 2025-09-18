class Solution {
    public int earliestTime(int[][] tasks) {
        int time = Integer.MAX_VALUE;
        for (int[] t : tasks) {
            int s = t[0];
            int e = t[1];
            time = Math.min(time, s + e);
        }
        return time;
    }
}