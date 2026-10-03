class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        
        // Base Case: A valid parenthesis string needs at least 2 characters "()"
        if (n <= 1) {
            return 0;
        }

        int res = 0;
        int open = 0, close = 0;

        // -----------------------------------------------------------------
        // PASS 1: Left ---> Right
        // Goal: Find valid substrings and handle excess closing brackets ')'
        // -----------------------------------------------------------------
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            // Condition A: Perfectly balanced prefix found
            if (open == close) {
                // Total length = open + close (or 2 * close)
                res = Math.max(res, open + close);
            } 
            // Condition B: Invalid state! Too many closing brackets (e.g., ")()")
            // Why reset? A valid substring can NEVER span across an unmatched ')'
            else if (close > open) {
                open = 0;
                close = 0;
            }
        }

        // -----------------------------------------------------------------
        // PASS 2: Right ---> Left
        // Goal: Catch cases missed by Pass 1 due to excess opening brackets
        // Example flaw in Pass 1: "((()" -> Pass 1 never sees open == close.
        // Scanning backwards treats '(' as the terminating invalid character.
        // -----------------------------------------------------------------
        open = 0;
        close = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            // Condition A: Perfectly balanced suffix found
            if (open == close) {
                res = Math.max(res, open + close);
            } 
            // Condition B: Invalid state when moving left! Too many opening brackets (e.g., "(((")
            else if (open > close) {
                open = 0;
                close = 0;
            }
        }

        return res;
    }
}


class Solution_Bottom_Up {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n <= 1) return 0;

        // STATE DEFINITION:
        // dp[i] = length of the longest valid parentheses substring strictly ENDING at index i.
        // Note: If s[i] == '(', dp[i] is automatically 0 because a valid substring cannot end with '('.
        int[] dp = new int[n];
        int maxLen = 0;

        // Start from index 1 (index 0 alone can never form a valid pair)
        for (int i = 1; i < n; i++) {
            
            // We ONLY care about indices where s[i] == ')'
            if (s.charAt(i) == ')') {
                
                // ---------------------------------------------------------
                // CASE 1: Immediate pair pattern "...()"
                // Example: s = "()()" at i = 3
                // ---------------------------------------------------------
                if (s.charAt(i - 1) == '(') {
                    // 2 for the current "()" pair
                    // + dp[i - 2] (whatever valid substring ended right before this pair)
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } 
                
                // ---------------------------------------------------------
                // CASE 2: Nested / Consecutive closing pattern "...))"
                // Example: s = "(())" at i = 3
                // ---------------------------------------------------------
                else {
                    // s[i - 1] is also ')', which ends its own valid substring of length dp[i - 1].
                    // Where is the matching '(' for our current ')' at index i?
                    // It must be placed right before that inner valid substring!
                    int matchIdx = i - dp[i - 1] - 1;
                    
                    // Check if matchIdx is within bounds and actually holds an opening bracket '('
                    if (matchIdx >= 0 && s.charAt(matchIdx) == '(') {
                        
                        // Look for any valid substring that ended right BEFORE matchIdx
                        int prevValid = (matchIdx >= 1) ? dp[matchIdx - 1] : 0;
                        
                        // Total length combine three parts:
                        // 1. dp[i - 1]   -> Inner valid substring
                        // 2. + 2         -> The outer matching pair at matchIdx and i
                        // 3. + prevValid -> Neighboring valid substring before matchIdx
                        dp[i] = dp[i - 1] + 2 + prevValid;
                    }
                }
                
                // Track the overall maximum length found across the array
                maxLen = Math.max(maxLen, dp[i]);
            }
        }

        return maxLen;
    }
}