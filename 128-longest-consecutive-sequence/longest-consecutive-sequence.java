class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int num : nums)
            map.put(num, Boolean.FALSE);

        int maxLen = 0;

        for (int num : nums) {
            int currLen = 1;
            int nextNum = num + 1;
            while (map.containsKey(nextNum) && map.get(nextNum) == false) {
                currLen++;
                map.put(nextNum, Boolean.TRUE);
                nextNum++;
            }
            int prevNum = num - 1;
            while (map.containsKey(prevNum) && map.get(prevNum) == false) {
                currLen++;
                map.put(prevNum, Boolean.TRUE);
                prevNum--;
            }
            maxLen = Math.max(maxLen, currLen);
        }
        return maxLen;
    }
}