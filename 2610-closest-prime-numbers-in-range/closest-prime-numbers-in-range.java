class Solution {
    public int[] closestPrimes(int left, int right) {
        // Sieve algorithm to find prime numbers between [1,right]
        boolean[] prime = new boolean[right + 1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        for(int i = 2; i * i <= right; i++) {
            if(prime[i]) {
                for(int p = i * i; p <= right; p += i) {
                    prime[p] = false;
                }
            }
        }
        // find min diff b/w pair of prime numbers
        int[] res = new int[]{-1, -1};
        int minDiff = Integer.MAX_VALUE;
        int prev = -1;
        for(int i = left; i <= right; i++) {
            if(prime[i]) {
                if(prev == -1) prev = i;
                else {
                    if(i - prev < minDiff) {
                        res[0] = prev;
                        res[1] = i;
                        minDiff = i - prev;
                    }
                    prev = i;
                }
            }
        }
        return res;
    }
}