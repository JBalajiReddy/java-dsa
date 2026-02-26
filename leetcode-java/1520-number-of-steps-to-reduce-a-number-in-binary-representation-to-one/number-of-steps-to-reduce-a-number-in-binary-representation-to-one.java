class Solution {
    public int numSteps(String s) {
        int n = s.length(), steps = 0;
        int carry = 0;
        for (int i = n - 1; i >= 1; i--) {
            int digit = s.charAt(i) - '0' + carry;
            if (digit % 2 == 0) {
                steps++;
            } else {
                steps += 2;
                carry = 1;
            }
        }
        return steps + carry;
    }
}