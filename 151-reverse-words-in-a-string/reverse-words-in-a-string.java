class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int end = s.length() - 1;

        while (end >= 0) {
            // Skip trailing spaces
            while (end >= 0 && s.charAt(end) == ' ') end--;

            if (end < 0) break;

            int start = end;
            // Find the start of the word
            while (start >= 0 && s.charAt(start) != ' ') start--;

            // Append the word
            sb.append(s.substring(start + 1, end + 1));
            sb.append(' ');

            end = start - 1;
        }

        // Remove the trailing space
        return sb.toString().trim();
    }
}



// class Solution {
//     public String reverseWords(String s) {
//         String[] sp = s.split("\\s+");
//         StringBuilder sb = new StringBuilder();
//         for (int i = sp.length - 1; i >= 0; i--) {
//             sb.append(sp[i]);
//             sb.append(" ");
//         }
//         return sb.toString().trim();
//     }
// }