//Cantor's diagonal argument
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            Character ch = nums[i].charAt(i);
            sb.append(ch == '0' ? '1' : '0');
        }
        return sb.toString();
    }
}

// class Solution {
// int n;
// Set<String> set = new HashSet<>();

// public String findDifferentBinaryString(String[] nums) {
// n = nums.length;
// for (String s : nums) {
// set.add(s);
// }
// return recur("");
// }

// public String recur(String currStr) {
// if (currStr.length() == n) {
// if (!set.contains(currStr)) {
// return currStr;
// }
// return "";
// }
// String addZero = recur(currStr + "0");

// if (addZero.length() > 0)
// return addZero;

// return recur(currStr + "1");
// }
// }