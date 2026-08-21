class Solution {
    public int findJudge(int n, int[][] trust) {
        // Net trust count array for people 1 to n
        int[] trustScore = new int[n + 1];

        for (int[] t : trust) {
            int a = t[0]; // Person who trusts
            int b = t[1]; // Person being trusted
            
            trustScore[a]--; // Outgoing trust reduces score
            trustScore[b]++; // Incoming trust increases score
        }

        // The judge must have a net score of n - 1
        for (int i = 1; i <= n; i++) {
            if (trustScore[i] == n - 1) {
                return i;
            }
        }

        return -1;
    }
}