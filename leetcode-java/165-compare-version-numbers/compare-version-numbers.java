class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int n1 = v1.length;
        int n2 = v2.length;
        int maxLen = Math.max(n1, n2);

        for (int i = 0; i < maxLen; i++) {
            int ver1 = (i < n1) ? Integer.parseInt(v1[i]) : 0;
            int ver2 = (i < n2) ? Integer.parseInt(v2[i]) : 0;
            if (ver1 < ver2)
                return -1;
            else if (ver1 > ver2)
                return 1;
        }

        return 0;
    }
}