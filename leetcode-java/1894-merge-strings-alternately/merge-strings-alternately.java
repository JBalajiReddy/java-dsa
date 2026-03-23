public class Solution {
    public String mergeAlternately(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n || i < m; i++) {
            if (i < n) {
                res.append(word1.charAt(i));
            }
            if (i < m) {
                res.append(word2.charAt(i));
            }
        }
        return res.toString();
    }
}

// public class Solution {
//     public String mergeAlternately(String word1, String word2) {
//         StringBuilder res = new StringBuilder();
//         int i = 0, j = 0;
//         while (i < word1.length() && j < word2.length()) {
//             res.append(word1.charAt(i++));
//             res.append(word2.charAt(j++));
//         }
//         res.append(word1.substring(i));
//         res.append(word2.substring(j));
//         return res.toString();
//     }
// }