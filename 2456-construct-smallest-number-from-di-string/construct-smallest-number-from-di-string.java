//recursion
class Solution {
    public String smallestNumber(String pattern) {
        StringBuilder res = new StringBuilder();
        recur(0, 0, pattern.toCharArray(), res);
        return res.reverse().toString();
    }

    int recur(int currIdx, int currCount, char[] patternArr, StringBuilder res) {
        if (currIdx != patternArr.length) {
            if (patternArr[currIdx] == 'I') {
                recur(currIdx + 1, currIdx + 1, patternArr, res);
            } else {
                currCount = recur(currIdx + 1, currCount, patternArr, res);
            }
        }
        res.append(currCount + 1);
        return currCount + 1;
    }
}

// //Stack
// class Solution {
//     public String smallestNumber(String pattern) {
//         StringBuilder res = new StringBuilder();
//         Stack<Integer> stack = new Stack<>();

//         for (int i = 0; i <= pattern.length(); i++) {
//             stack.push(i + 1);
//             if (i == pattern.length() || pattern.charAt(i) == 'I') {
//                 while (!stack.isEmpty())
//                     res.append(stack.pop());
//             }
//         }
//         return res.toString();
//     }
// }