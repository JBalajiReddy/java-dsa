class Solution {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        List<String> list = new ArrayList<>();
        int n = words.length;
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (groups[i] != prev) {
                list.add(words[i]);
                prev = groups[i];
            }
        }
        return list;
    }
}


// class Solution {
//     public List<String> getLongestSubsequence(String[] words, int[] groups) {
//         List<String> list = new ArrayList<>();
//         int n = words.length;
//         if (n > 0)
//             list.add(words[0]);
//         for (int i = 1; i < n; i++) {
//             if (groups[i] != groups[i - 1]) {
//                 list.add(words[i]);
//             }
//         }
//         return list;
//     }
// }