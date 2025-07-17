class Solution {
    private List<List<Integer>> res;
    private int n;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        n = candidates.length;
        res = new ArrayList<>();
        List<Integer> ls = new ArrayList<>();
        backtrack(ls, 0, candidates, target);
        return res;
    }

    private void backtrack(List<Integer> ls, int i, int[] nums, int t) {
        if (t == 0) {
            res.add(new ArrayList<>(ls));
            return;
        }
        if (t < 0 || i >= n)
            return;

        ls.add(nums[i]);
        backtrack(ls, i, nums, t - nums[i]);

        ls.remove(ls.size() - 1);
        backtrack(ls, i + 1, nums, t);
    }
}