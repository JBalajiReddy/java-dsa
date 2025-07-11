class Solution {
    public int findLHS(int[] nums) {
        Map<Long, Integer> map = new HashMap<>();
        for (long num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        int len = 0;
        for (long key : map.keySet()) {
            if (map.containsKey(key + 1))
                len = Math.max(len, map.get(key) + map.get(key + 1));
        }
        return len;
    }
}