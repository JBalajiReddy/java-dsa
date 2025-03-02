class Solution {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] num : items1) {
            map.put(num[0], num[1]);
        }
        for (int[] num : items2) {
            map.put(num[0], map.getOrDefault(num[0], 0) + num[1]);
        }

        for (int val : map.keySet()) {
            List<Integer> list = new ArrayList<>();
            list.add(val);
            list.add(map.get(val));
            res.add(list);
        }
        return res;

    }
}