class Solution {
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            char ch = nums[i].charAt(i);
            sb.append((ch == '0') ? '1' : '0');
        }
        return sb.toString();
    }
}

//Cantor's Diagonal Argument