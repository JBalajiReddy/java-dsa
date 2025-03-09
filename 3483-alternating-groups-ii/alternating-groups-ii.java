class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;
        int count = 0;
        int maxAlterSeq = 1;
        for(int i = 1; i < n + k -1; i++) {
            int currIdx = i % n;
            int prevIdx = (i - 1) % n;

            if(colors[currIdx] != colors[prevIdx]) {
                maxAlterSeq++;
            } else {
                if(maxAlterSeq >= k) {
                    count += (maxAlterSeq - k + 1);
                }
                maxAlterSeq = 1;
            }
        }
        if(maxAlterSeq >= k) {
            count += (maxAlterSeq - k + 1);
        }
        return count;
    }
}