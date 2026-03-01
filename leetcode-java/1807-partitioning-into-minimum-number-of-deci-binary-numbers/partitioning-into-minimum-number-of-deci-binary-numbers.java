class Solution {
    public int minPartitions(String n) {
        int maxD = 0;
        for (int i = 0; i < n.length(); i++) {
            maxD = Math.max(maxD, n.charAt(i) - '0');
            if (maxD == 9)
                return 9;
        }
        return maxD;
    }
}