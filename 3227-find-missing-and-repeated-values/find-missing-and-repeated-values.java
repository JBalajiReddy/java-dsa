class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
       // Map<Integer, Integer> mp = new HashMap<>();
       int[] freq = new int[(n * n) + 1];
        int gSum = 0;
        for (int[] g : grid) {
            for (int i = 0; i < g.length; i++) {
                // mp.put(num, mp.getOrDefault(num, 0) + 1);
                freq[g[i]]++;
                gSum += g[i];
            }
        }
       int repeatedVal = getMaxFreq(freq);
       int actualSum = (n * n * (n * n + 1)) / 2;
       int missingVal = actualSum - (gSum - repeatedVal);
       return new int[]{repeatedVal, missingVal};
    }

    private int getMaxFreq(int[] freq) {
        int n = 0; 
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] == 2 || freq[i] > 1) { 
                n = i;
                break;
            }
        }
        return n;
    }
}