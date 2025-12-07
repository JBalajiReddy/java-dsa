class Solution {
    public int countOdds(int low, int high) {
        if (high == 0)
            return 0;
        else if (low % 2 == 0 && high % 2 == 0)
            return (int) (high - low) / 2;
        return (int) (high - low) / 2 + 1;
    }
}