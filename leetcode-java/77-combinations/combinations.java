class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(n, k, 1, res, new ArrayList<>());
        return res;
    }

    private void backtrack(int n, int k, int i, List<List<Integer>> res, List<Integer> ls) {
        if (ls.size() == k) {
            res.add(new ArrayList<>(ls));
            return;
        }

        if (i > n) {
            return;
        }

        // Pruning Optimization: Ensure enough remaining elements exist to reach size k
        // Need (k - ls.size()) more elements from numbers in range [num, n]
        for (int num = i; num <= n - (k - ls.size()) + 1; num++) {
            ls.add(num);
            backtrack(n, k, num + 1, res, ls);
            ls.remove(ls.size() - 1);
        }
    }
}