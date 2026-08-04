class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> ls = new ArrayList<>();
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }
        for (int i = nums[0]; i < nums[nums.length - 1]; i++) {
            if (!set.contains(i)) {
                ls.add(i);
            }
        }
        return ls;
    }
}