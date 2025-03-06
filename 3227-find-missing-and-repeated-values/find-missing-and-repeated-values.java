class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int grid_sum = 0;
        int[] res = new int[2];
        for (int[] num : grid) {
            for (int i = 0; i < num.length; i++) {
                map.put(num[i], map.getOrDefault(num[i], 0) + 1);
                grid_sum += num[i];
            }
        }
        int n = grid.length * grid[0].length;
        int actual_sum = n * (n + 1) / 2;
        int repeat_no = getKeyByValue(map, 2);
        int missing_no = repeat_no - (grid_sum - actual_sum);
        res[0] = repeat_no;
        res[1] = missing_no;
        return res;
    }

    private Integer getKeyByValue(HashMap<Integer, Integer> map, int value) {
        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == value) {
                return entry.getKey();
            }
        }
        return null;
    }
}