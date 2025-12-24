class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int n = apple.length, m = capacity.length;  
        int sum = 0;
        for (int a : apple) sum += a;
        Arrays.sort(capacity);
        int cnt = 0, i = m - 1;
        while (sum > 0) {
            sum = sum - capacity[i];
            cnt++;
            i--;
        }
        return cnt;
    }
}