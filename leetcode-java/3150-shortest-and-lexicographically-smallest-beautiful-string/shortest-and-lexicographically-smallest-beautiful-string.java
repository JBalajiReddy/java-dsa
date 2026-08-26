class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String ans = "";
        int left = 0, cnt = 0;

        for (int right = 0; right < n; right++) {
            // Include right character
            cnt += s.charAt(right) - '0';

            // Shrink window if count exceeds k OR if left character is '0' (leading zero)
            while (cnt > k || (left <= right && s.charAt(left) == '0')) {
                cnt -= s.charAt(left) - '0';
                left++;
            }

            // Valid candidate found
            if (cnt == k) {
                String tmp = s.substring(left, right + 1);
                
                // First valid substring OR shorter OR lexicographically smaller
                if (ans.equals("") || tmp.length() < ans.length() || 
                   (tmp.length() == ans.length() && tmp.compareTo(ans) < 0)) {
                    ans = tmp;
                }
            }
        }

        return ans;
    }
}