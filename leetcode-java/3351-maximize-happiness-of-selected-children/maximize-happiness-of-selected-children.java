class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        int n = happiness.length;
        Arrays.sort(happiness);
        long res = 0;
        int turns = 0;
        for (int i = n - 1; i >= 0; i--) {
            res += Math.max(happiness[i] - turns, 0);
            turns++;
            k--;
            if (k == 0)
                break;
        }
        return res;
    }
}