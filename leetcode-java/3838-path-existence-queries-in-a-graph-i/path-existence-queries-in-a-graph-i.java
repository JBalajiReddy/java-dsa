class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] comp = new int[n];
        comp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] <= maxDiff) {
                comp[i] = comp[i - 1]; //belong to same comp
            } else {
                comp[i] = comp[i - 1] + 1; //diff comp
            }
        }

        boolean[] res = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = (comp[queries[i][0]] == comp[queries[i][1]]);
        }
        return res;
    }
}