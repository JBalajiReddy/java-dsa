class Solution {
    public String reverseWords(String s) {
        String st[] = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String sub : st) {
            StringBuilder rev = new StringBuilder(sub).reverse();
            sb.append(rev);
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}