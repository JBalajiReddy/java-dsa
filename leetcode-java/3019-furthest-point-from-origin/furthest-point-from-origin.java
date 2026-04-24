class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int d = 0;
        int c = 0;
        for (char ch : moves.toCharArray()) {
            if (ch == 'L') {
                d--;
            } else if (ch == 'R') {
                d++;
            } else {
                c++;
            }
        }
        return d < 0 ? -d + c : d + c;
    }
}