class Solution {
    public int findCenter(int[][] edges) {
        int p1[] = edges[0];
        int p2[] = edges[1];
        if (p1[0] == p2[0] || p1[0] == p2[1])
            return p1[0];
        else
            return p1[1];
    }
}