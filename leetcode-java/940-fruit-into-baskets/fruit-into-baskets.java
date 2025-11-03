//Find the longest continuous sub array that has exactly 2 distinct elements.
class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int left = 0, right = 0, maxLen = 0;

        Map<Integer, Integer> mp = new HashMap<>();
        while (right < n) {
            mp.put(fruits[right], mp.getOrDefault(fruits[right], 0) + 1);

            if (mp.size() > 2) {
                // while (mp.size() > 2) {
                    mp.put(fruits[left], mp.getOrDefault(fruits[left], 0) - 1);
                    if (mp.get(fruits[left]) == 0)
                        mp.remove(fruits[left]);

                    left++;
                // }
            }

            if (mp.size() <= 2) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
            right++;
        }
        return maxLen;
    }
}