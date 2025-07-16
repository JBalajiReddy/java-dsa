class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ls = new ArrayList<>();
        backtrack(0, nums.length, ls, res, nums);
        return res;
    }

    private void backtrack(int i, int n, List<Integer> ls, List<List<Integer>> res, int[] nums) {
        if (i == n) {
            res.add(new ArrayList<>(ls));
            return;
        }

        for (int idx = i; idx < n; idx++) {
            swap(nums, i, idx); // Swap the current index with idx
            ls.add(nums[i]); 
            backtrack(i + 1, n, ls, res, nums); // Recurse for the next index


            ls.remove(ls.size() - 1); // Backtrack: remove the last added number
            swap(nums, i, idx); // Swap back to restore the original array
        }

    }

    private void swap(int[] nums, int i, int idx) {
        int tmp = nums[i];
        nums[i] = nums[idx];
        nums[idx] = tmp;
    }
}