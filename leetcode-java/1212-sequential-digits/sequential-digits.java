class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String s = "123456789";
        List<Integer> ls = new ArrayList<>();
        int minLen = String.valueOf(low).length();
        int maxLen = String.valueOf(high).length();
        for (int len = minLen; len <= maxLen; len++) {
            for (int start = 0; start <= 9 - len; start++) {
                int num = Integer.parseInt(s.substring(start, start + len));
                if (num >= low && num <= high) {
                    ls.add(num);
                }
            }
        }
        return ls;
    }
}