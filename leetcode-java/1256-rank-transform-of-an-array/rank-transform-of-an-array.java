class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        TreeMap<Integer, List<Integer>> mp = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            mp.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        int rank = 1;
        for (int nums : mp.keySet()) {
            for (int idx : mp.get(nums)) {
                res[idx] = rank;
            }
            rank++;
        }
        return res;
    }
}