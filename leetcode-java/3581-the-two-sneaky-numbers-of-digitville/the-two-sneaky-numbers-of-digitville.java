class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int[] freq = new int[nums.length];
        for (int n : nums) {
            if (n >= 0 && n < nums.length) { 
                freq[n]++;
            }
        }

        int[] res = new int[2];
        List<Integer> ls = new ArrayList<>();

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] == 2) {
               ls.add(i);
            }
        }

        res[0] = (ls.size() > 0) ? ls.get(0) : -1;
        res[1] = (ls.size() > 1) ? ls.get(1) : -1;
        
        return res;
    }
}