class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> pos = new HashMap<>();
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            
            // If current number matches a previously stored reversed number, calculate distance
            if (pos.containsKey(x)) {
                minDist = Math.min(minDist, i - pos.get(x));
            }
            
            // Store the reversed current number with its most recent index
            pos.put(reverse(x), i); 
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    private int reverse(int x) {
        int y = 0;
        while (x > 0) {
            y = (y * 10) + (x % 10);
            x /= 10;
        }
        return y;
    }
}