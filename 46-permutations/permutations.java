class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i]))
                    continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}

// class Solution {
//     public List<List<Integer>> permute(int[] nums) {
//         List<List<Integer>> res = new ArrayList<>();
//         List<Integer> ls = new ArrayList<>();
//         backtrack(0, nums.length, ls, res, nums);
//         return res;
//     }

//     private void backtrack(int i, int n, List<Integer> ls, List<List<Integer>> res, int[] nums) {
//         if (i == n) {
//             res.add(new ArrayList<>(ls));
//             return;
//         }

//         for (int idx = i; idx < n; idx++) {
//             swap(nums, i, idx); // Swap the current index with idx
//             ls.add(nums[i]); 
//             backtrack(i + 1, n, ls, res, nums); // Recurse for the next index

//             ls.remove(ls.size() - 1); // Backtrack: remove the last added number
//             swap(nums, i, idx); // Swap back to restore the original array
//         }

//     }

//     private void swap(int[] nums, int i, int idx) {
//         int tmp = nums[i];
//         nums[i] = nums[idx];
//         nums[idx] = tmp;
//     }
// }