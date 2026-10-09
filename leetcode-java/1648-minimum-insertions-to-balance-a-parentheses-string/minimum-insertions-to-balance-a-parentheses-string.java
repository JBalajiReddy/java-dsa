class Solution1 {
    public int minInsertions(String s) {
        int insertions = 0;
        int leftCount = 0; // Number of unmatched '('
        int length = s.length();
        int index = 0;

        while (index < length) {
            char c = s.charAt(index);

            if (c == '(') {
                leftCount++; // Need 2 ')' for this '('
                index++;
            } else {
                // Case 1: We encountered a ')'
                if (leftCount > 0) {
                    leftCount--; // Match this pair of '))' with one previous '('
                } else {
                    insertions++; // No '(' available, so we must insert one '('
                }

                // Lookahead check: Do we have a second consecutive ')'?
                if (index < length - 1 && s.charAt(index + 1) == ')') {
                    index += 2; // Matched a full "))" pair, advance by 2
                } else {
                    insertions++; // Only saw a single ')', must insert the second ')'
                    index++; // Advance by 1
                }
            }
        }

        // Each leftover '(' requires 2 closing ')'
        insertions += leftCount * 2;
        return insertions;
    }
}

class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededRight = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                neededRight += 2;

                // If neededRight is odd, we insert 1 ')' to complete a pair first
                if (neededRight % 2 != 0) {
                    insertions++;
                    neededRight--;
                }
            } else { // c == ')'
                neededRight--;

                // If neededRight becomes -1, we got an extra ')' without a '('
                if (neededRight < 0) {
                    insertions++; // Insert 1 '('
                    neededRight += 2; // Adding '(' demands 2 ')', minus the 1 we just saw => +1 net
                }
            }
        }

        return insertions + neededRight;
    }
}