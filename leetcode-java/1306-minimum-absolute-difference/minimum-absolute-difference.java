class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(arr);
        int min = 1000_000_000;

        for (int i = 0; i < arr.length - 1; i++) {
            int diff = arr[i + 1] - arr[i];
            if (diff < min) {
                min = diff;
                list.clear();
            }
            if (diff == min) {
                list.add(Arrays.asList(arr[i], arr[i + 1]));
            }
        }
        return list;
    }
}