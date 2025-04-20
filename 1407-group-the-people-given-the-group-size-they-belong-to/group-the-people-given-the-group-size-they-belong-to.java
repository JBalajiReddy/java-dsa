class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int size = groupSizes[i];

            map.putIfAbsent(size, new ArrayList<Integer>());
            map.get(size).add(i);

            if (map.get(size).size() == size) {
                res.add(map.get(size));
                map.remove(size);
            }
        }
        return res;
    }
}