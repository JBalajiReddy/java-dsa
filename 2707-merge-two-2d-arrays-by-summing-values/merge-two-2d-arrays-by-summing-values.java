class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num[] : nums1) {
            map.put(num[0], num[1]);
        }
        for (int num[] : nums2) {
            map.put(num[0], map.getOrDefault(num[0], 0) + num[1]);
        }

        List<int[]> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(new int[] { entry.getKey(), entry.getValue() });
        }

        list.sort(Comparator.comparingInt(a -> a[0]));

        return list.toArray(new int[list.size()][2]);
    }
}