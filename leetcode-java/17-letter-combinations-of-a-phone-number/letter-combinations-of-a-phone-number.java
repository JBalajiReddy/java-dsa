class Solution {
    private static final String[] MAP = {
            "", // 0
            "", // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs", // 7
            "tuv", // 8
            "wxyz" // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        // Edge case: Empty input string
        if (digits == null || digits.length() == 0) {
            return res;
        }

        backtrack(digits, 0, new StringBuilder(), res);
        return res;
    }

    private void backtrack(String digits, int idx, StringBuilder curr, List<String> res) {
        // BASE CASE: Reached the length of digits
        if (idx == digits.length()) {
            res.add(curr.toString());
            return;
        }

        // Get the string mapping for the current digit character
        String letters = MAP[digits.charAt(idx) - '0'];

        // EXPLORE CHOICES
        for (int i = 0; i < letters.length(); i++) {
            // Choice: Append character
            curr.append(letters.charAt(i));

            // Recurse: Move to next digit index
            backtrack(digits, idx + 1, curr, res);

            // Undo Choice (Backtrack): Delete last added character
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}