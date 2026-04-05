class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for (char ch : moves.toCharArray()) {
            if (ch == 'U') {
                x--;
            } else if (ch == 'R') {
                y++;
            } else if (ch == 'D') {
                x++;
            } else {
                y--;
            }
        }
        return x == 0 && y == 0;
    }
}