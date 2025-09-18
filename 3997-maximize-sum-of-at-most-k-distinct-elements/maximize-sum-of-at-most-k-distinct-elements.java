class Solution {
    public int[] maxKDistinct(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);
        List<Integer> ls = new ArrayList<>(set);
        Collections.sort(ls, Collections.reverseOrder());
        int s = Math.min(k, ls.size());
        int[] res = new int[s];
        for (int i = 0; i < s; i++) res[i] = ls.get(i);
        return res;
    }
}