class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int n = digits.length;
        Set<Integer> set = new HashSet<>();
        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                for (int k = 0; k < n; k++) {
                    if (i == k || j == k)
                        continue;
                    if (digits[k] % 2 == 0 && digits[i] != 0) {
                        num = digits[i] * 100 + digits[j] * 10 + digits[k];
                        set.add(num);
                    }
                }
            }
        }
        int[] res = new int[set.size()];
        int i = 0;
        for (Integer r : set) {
            res[i++] = r;
        }
        Arrays.sort(res);
        return res;
    }
}