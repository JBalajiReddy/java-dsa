class Solution {
    public int minCost(String colors, int[] neededTime) {
        int n = colors.length();
        int totalCost = 0;
        for (int i = 0; i < n; i++) {
            totalCost += neededTime[i];
        }

        int maxSum = 0;
        int currMax = neededTime[0];
        for (int i = 1; i < n; i++) {
            if (colors.charAt(i - 1) == colors.charAt(i)) {
                currMax = Math.max(currMax, neededTime[i]);
            } else {
                maxSum += currMax;
                currMax = neededTime[i];
            }
        }

        maxSum += currMax; //last group
        return totalCost - maxSum;
    }
}