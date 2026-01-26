class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length, minD = 1000_000_000; 
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < n; i++) {
            minD = Math.min(minD, (arr[i] - arr[i - 1]));
            set.add(arr[i]);
        }
        List<List<Integer>> ls = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (set.contains(minD + arr[i])) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(arr[i]);
                tmp.add(arr[i] + minD);
                ls.add(tmp);
            }
        }
        return ls;
    }
}