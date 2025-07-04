class Solution {
    public String removeOccurrences(String s, String part) {
        StringBuilder sb = new StringBuilder();
        int partLen = part.length();

        for (char c : s.toCharArray()) {
            sb.append(c);
            if (sb.length() >= partLen &&
                    sb.substring(sb.length() - partLen).equals(part)) {
                sb.setLength(sb.length() - partLen); // remove the part
            }
        }

        return sb.toString();
    }
}

// class Solution {
//     public String removeOccurrences(String s, String part) {
//         char[] input = s.toCharArray();
//         char[] target = part.toCharArray();
//         char[] resultStack = new char[input.length];
//         int stackSize = 0, targetLength = target.length;
//         char targetEndChar = target[targetLength - 1];

//         for (char currentChar : input) {
//             resultStack[stackSize++] = currentChar;

//             if (currentChar == targetEndChar && stackSize >= targetLength) {
//                 int i = stackSize - 1, j = targetLength - 1;

//                 while (j >= 0 && resultStack[i] == target[j]) {
//                     i--;
//                     j--;
//                 }

//                 if (j < 0) {
//                     stackSize = i + 1;
//                 }
//             }
//         }
//         return new String(resultStack, 0, stackSize);
//     }
// }

// class Solution {
//     public String removeOccurrences(String s, String part) {
//         int idx = s.indexOf(part);
//         while (idx != -1) {
//             s = s.substring(0, idx) + s.substring(idx + part.length());
//             idx = s.indexOf(part);
//         }
//         return s;
//     }
// }