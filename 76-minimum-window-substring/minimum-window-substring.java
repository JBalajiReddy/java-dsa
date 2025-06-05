class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length())
            return "";
        if (s == t)
            return s;

        Map<Character, Integer> totalCts = new HashMap<>();
        for (char ch : t.toCharArray()) {
            totalCts.put(ch, totalCts.getOrDefault(ch, 0) + 1);
        }

        int req = totalCts.size();
        int formed = 0;
        int left = 0, right = 0;
        int minLeft = 0, minLen = Integer.MAX_VALUE;
        Map<Character, Integer> currCts = new HashMap<>();

        while (right < s.length()) {
            char currChar = s.charAt(right);
            currCts.put(currChar, currCts.getOrDefault(currChar, 0) + 1);
            if (totalCts.containsKey(currChar) &&
                    currCts.get(currChar).intValue() == totalCts.get(currChar).intValue()) {
                formed++;
            }
            while (left <= right && formed == req) {
                char leftChar = s.charAt(left);
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }
                currCts.put(leftChar, currCts.get(leftChar) - 1);
                if (totalCts.containsKey(leftChar) &&
                        currCts.get(leftChar).intValue() < totalCts.get(leftChar).intValue()) {
                    formed--;
                }

                left++;
            }

            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
}