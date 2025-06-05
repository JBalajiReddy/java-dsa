class Solution {
    public String minWindow(String s, String t) {

        if (t.length() > s.length())
            return "";
        if (s == t)
            return s;

        Map<Character, Integer> totalCts = new HashMap<>();
        for (char ch : t.toCharArray()) {
            totalCts.put(ch, totalCts.getOrDefault(ch, 0) + 1);
        }

        int required = totalCts.size(); 
        int formed = 0; 
        Map<Character, Integer> currentCts = new HashMap<>();

        int left = 0, right = 0; 
        int minLength = Integer.MAX_VALUE; 
        int minLeft = 0; 

        while (right < s.length()) {
            char currentChar = s.charAt(right);
            currentCts.put(currentChar, currentCts.getOrDefault(currentChar, 0) + 1);

            if (totalCts.containsKey(currentChar) &&
                    currentCts.get(currentChar).intValue() == totalCts.get(currentChar).intValue()) {
                formed++;
            }
       
            //finding min window
            while (left <= right && formed == required) {
                char leftChar = s.charAt(left);

                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }

                currentCts.put(leftChar, currentCts.get(leftChar) - 1);
                if (totalCts.containsKey(leftChar) &&
                        currentCts.get(leftChar).intValue() < totalCts.get(leftChar).intValue()) {
                    formed--;
                }

                left++; 
            }

            right++; 
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
    }
}