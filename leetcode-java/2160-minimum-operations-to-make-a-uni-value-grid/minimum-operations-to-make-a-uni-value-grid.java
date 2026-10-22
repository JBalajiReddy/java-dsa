class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> ls = new ArrayList<>();
        for (int[] row : grid) {
            for (int colEle : row) {
                ls.add(colEle);
            }
        }

        Collections.sort(ls);
        int n = ls.size();
        int target = ls.get(n / 2); //target is median (mid) element to minimize num of ops

        int ops = 0;
        for (int num : ls) {
            if (target % x != num % x) {
                return -1;
            }

            ops += Math.abs(target - num) / x;
        }
        return ops;
    }
}