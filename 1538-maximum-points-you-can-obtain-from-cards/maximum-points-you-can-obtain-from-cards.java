class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int maxSum = 0, lSum = 0;
        //left sum 0 -> k -1
        for (int i = 0; i < k; i++) {
            lSum += cardPoints[i];
        }
        maxSum = lSum;

        int rSum = 0, rIdx = cardPoints.length - 1;
        for (int i = k - 1; i >= 0; i--) {
            lSum -= cardPoints[i];
            rSum += cardPoints[rIdx];
            rIdx--;

            maxSum = Math.max(maxSum, lSum + rSum); 
        }
        return maxSum;
    }
}