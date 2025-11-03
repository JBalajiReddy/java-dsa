class Solution {
    private List<List<Integer>> res;
    private int n;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        n = candidates.length;
        res = new ArrayList<>();
        List<Integer> ls = new ArrayList<>();

        Arrays.sort(candidates);
        backtrack(ls, 0, candidates, target);
        return res;
    }

    private void backtrack(List<Integer> ls, int idx, int[] nums, int t) {
        if (t == 0) {
            res.add(new ArrayList<>(ls));
            return;
        }
        if (t < 0)
            return;

        for (int i = idx; i < n; i++) {
            if (i > idx && nums[i] == nums[i - 1])
                continue;

            if (nums[i] > t) break;
            ls.add(nums[i]);
            backtrack(ls, i + 1, nums, t - nums[i]);
            ls.remove(ls.size() - 1);
        }
    }
}