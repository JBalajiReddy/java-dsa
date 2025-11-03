class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null)
            return "";

        String pf = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String next = strs[i];
            while (!next.startsWith(pf)) {
                pf = pf.substring(0, pf.length() - 1);
            }

            if (pf.isEmpty())
                return "";
        }
        return pf;
    }
}