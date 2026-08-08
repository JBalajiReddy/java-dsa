class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, res);
        return res;
    }

    private void backtrack(int start, int[] nums, List<List<Integer>> res) {
        if (start == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums)
                list.add(num);
            res.add(list);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            backtrack(start + 1, nums, res);
            swap(nums, start, i); // Undo swap
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

class Solution_Backtracking {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backtrack(nums, visited, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(
            int[] nums, boolean[] visited, List<Integer> current, List<List<Integer>> res) {
        // Base Case: Full permutation constructed
        if (current.size() == nums.length) {
            res.add(new ArrayList<>(current));
            return;
        }

        // Loop over ALL elements (since order matters, we always start from index 0)
        for (int i = 0; i < nums.length; i++) {
            // Skip if element is already used in the current path
            if (visited[i]) {
                continue;
            }

            // Choose
            visited[i] = true;
            current.add(nums[i]);

            // Explore
            backtrack(nums, visited, current, res);

            // Unchoose (Backtrack)
            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }
}