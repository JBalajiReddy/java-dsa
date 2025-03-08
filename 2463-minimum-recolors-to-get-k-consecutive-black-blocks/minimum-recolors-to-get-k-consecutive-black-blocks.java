class Solution {
    public int minimumRecolors(String blocks, int k) {
        int left = 0, right = 0, reColor = Integer.MAX_VALUE, whites = 0;
        for (right = 0; right < blocks.length(); right++) {
            if (blocks.charAt(right) == 'W')
                whites++;
            if (right - left + 1 == k) {
                reColor = Math.min(whites, reColor);
                if(blocks.charAt(left) == 'W') whites--;
                left++;
            }
        }
        return reColor;
    }
}