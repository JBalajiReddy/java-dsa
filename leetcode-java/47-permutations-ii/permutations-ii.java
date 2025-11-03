class Solution {
    private List<List<Integer>> res;
    int n;
    public List<List<Integer>> permuteUnique(int[] nums) {
        n = nums.length;
        res = new ArrayList<>();
        List<Integer> ls = new ArrayList<>();
        Map<Integer, Integer> mp = new HashMap<>();

        for (int num : nums)
            mp.put(num, mp.getOrDefault(num, 0) + 1);

        backtrack( mp, ls);
        return res;
    }

    private void backtrack(Map<Integer, Integer> mp, List<Integer> ls) {
        if (ls.size() == n) {
            res.add(new ArrayList<>(ls));
            return;
        }

        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();

            if (count == 0) continue;
            
            //include
            ls.add(num);
            mp.put(num, count - 1); //reduce freq

            backtrack(mp, ls); //explore

            //backtrack - undo
            ls.remove(ls.size() - 1);
            mp.put(num, count);
        }
    }
}