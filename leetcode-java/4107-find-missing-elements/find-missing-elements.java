class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> ls = new ArrayList<>();
        int min = 101, max = 0;
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        for (int i = min + 1; i < max; i++) {
            if (!set.contains(i)) {
                ls.add(i);
            }
        }
        return ls;
    }
}