class Solution {
    public String defangIPaddr(String address) {
        String res = "";
        for (int i = 0; i < address.length(); i++) {
            char ch = address.charAt(i);
            if (ch == '.') {
                res += "[.]";
            } else {
                res += ch;
            }
        }
        return res;
    }
}