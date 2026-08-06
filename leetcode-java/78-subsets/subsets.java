class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        backtrack(nums, 0, tmp, res);
        return res;
    }

    private void backtrack(int[] nums, int i, List<Integer> tmp, List<List<Integer>> res) {
        if (i == nums.length) {
            res.add(new ArrayList<>(tmp)); //shallow copy since objects are mutable (they all point to same ref)
            return;
        }

        tmp.add(nums[i]);
        backtrack(nums, i + 1, tmp, res);
        tmp.remove(tmp.size() - 1);
        backtrack(nums, i + 1, tmp, res);
    }
}