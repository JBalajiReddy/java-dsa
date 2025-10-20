class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int res = 0;
        for (String s : operations) {
            if (s.equals("++X") || s.equals("X++")) res++;
            else res--;
        }
        return res;
    }
}