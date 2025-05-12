class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int[] count = new int[10];
        for (int digit : digits) {
            count[digit]++;
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            if (count[i] > 0) {
                count[i]--; // Use this digit
                for (int j = 0; j < 10; j++) {
                    if (count[j] > 0) {
                        count[j]--; // Use this digit
                        for (int k = 0; k < 10; k += 2) {
                            if (count[k] > 0) {

                                int num = i * 100 + j * 10 + k;
                                result.add(num);
                            }
                        }
                        count[j]++; // Backtrack
                    }
                }
                count[i]++; // Backtrack
            }
        }

        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        Arrays.sort(res);

        return res;
    }
}

// class Solution {
//     public int[] findEvenNumbers(int[] digits) {
//         int n = digits.length;
//         Set<Integer> set = new HashSet<>();
//         int num = 0;
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (i == j)
//                     continue;
//                 for (int k = 0; k < n; k++) {
//                     if (i == k || j == k)
//                         continue;
//                     if (digits[k] % 2 == 0 && digits[i] != 0) {
//                         num = digits[i] * 100 + digits[j] * 10 + digits[k];
//                         set.add(num);
//                     }
//                 }
//             }
//         }
//         int[] res = new int[set.size()];
//         int i = 0;
//         for (Integer r : set) {
//             res[i++] = r;
//         }
//         Arrays.sort(res);
//         return res;
//     }
// }