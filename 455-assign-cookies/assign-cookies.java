class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int cookie = 0, child = 0;
        while (child < g.length && cookie < s.length) {
            if (s[cookie] >= g[child]) {
                child++;
                cookie++;
            } else
                cookie++;
        }
        return child;
    }
}