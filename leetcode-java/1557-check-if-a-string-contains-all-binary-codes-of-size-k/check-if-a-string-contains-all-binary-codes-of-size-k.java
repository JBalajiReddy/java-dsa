class Solution {
    public boolean hasAllCodes(String s, int k) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i <= s.length() - k; i++) {
            String tmp = s.substring(i, i + k);
            set.add(tmp);
        }
        return (set.size() == Math.pow(2, k)); // 1 << k
    }
}