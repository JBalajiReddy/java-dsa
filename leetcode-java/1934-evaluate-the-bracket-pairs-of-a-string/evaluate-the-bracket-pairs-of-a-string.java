class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        int n = s.length();
        Map<String, String> mp = new HashMap<>();
        for (List<String> ls : knowledge) {
            mp.put(ls.get(0), ls.get(1));
        }
        StringBuilder res = new StringBuilder();
        int left = 0, right = 0;
        boolean isKey = false;
        while (right < n) {
            char ch = s.charAt(right);
            if (ch == '(') {
                left = right;
                isKey = true;
            } else if (ch == ')') {
                String sub = s.substring(left + 1, right);
                res.append(mp.getOrDefault(sub, "?"));
                isKey = false;
            } else {
                if (!isKey) {
                    res.append(ch);
                }
            }
            right++;
        }
        return res.toString();
    }
}