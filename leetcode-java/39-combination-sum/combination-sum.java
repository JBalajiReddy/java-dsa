class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int t) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ls = new ArrayList<>();
        backtrack(nums, t, 0, res, ls);
        return res;
    }

    private void backtrack(int[] nums, int t, int start, List<List<Integer>> res, List<Integer> ls) {
        if (t == 0) {
            res.add(new ArrayList<>(ls));
            return;
        }
        if (t < 0)
            return;

        for (int i = start; i < nums.length; i++) {
            ls.add(nums[i]);
            backtrack(nums, t - nums[i], i, res, ls); // Pass i for reuse
            ls.remove(ls.size() - 1);
        }
    }
}