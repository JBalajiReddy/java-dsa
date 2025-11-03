class FindSumPairs {

    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> map;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.map = new HashMap<>();
        for (int n : nums2)
            map.put(n, map.getOrDefault(n, 0) + 1);
    }

    public void add(int index, int val) {
        int oldVal = nums2[index];
        map.put(oldVal, map.getOrDefault(oldVal, 0) - 1);
        nums2[index] += val;
        map.put(nums2[index], map.getOrDefault(nums2[index], 0) + 1);
    }

    public int count(int tot) {
        int res = 0;
        for (int n : nums1) {
            int num = tot - n;
            res += map.getOrDefault(num, 0);
        }
        return res;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */