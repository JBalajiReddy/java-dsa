class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ls = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(0, nums.length, ls, res, nums);
        return res;
    }

    private void backtrack(int i, int n, List<Integer> ls, List<List<Integer>> res, int[] nums) {
        if (i == n) {
            res.add(new ArrayList<>(ls));  // Create a new list to avoid reference issues
            return;
        }

        //include
        ls.add(nums[i]);
        backtrack(i + 1, n, ls, res, nums);

        //backtrack
        ls.remove(ls.size() - 1);

        //skip duplicates
        int idx = i + 1;
        while (idx < n && nums[idx] == nums[idx - 1]) idx++;

        backtrack(idx, n, ls, res, nums);
    }
}