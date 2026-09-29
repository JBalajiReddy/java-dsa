class Solution {
    public String defangIPaddr(String address) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < address.length(); i++) {
            char ch = address.charAt(i);
            if (ch == '.') {
                res.append("[.]");
            } else {
                res.append(ch);
            }
        }
        return res.toString();
    }
}