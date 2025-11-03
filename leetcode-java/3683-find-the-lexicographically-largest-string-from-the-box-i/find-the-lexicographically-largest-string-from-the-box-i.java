class Solution {
    public String answerString(String word, int numFriends) {
        String res = "";
        if (numFriends == 1)
            return word;
        int len = word.length() - numFriends + 1;
        for (int i = 0; i < word.length(); i++) {
            String temp;
            if (i + len <= word.length()) {
                temp = word.substring(i, i + len);
            } else {
                temp = word.substring(i);
            }
            if (temp.compareTo(res) > 0)
                res = temp;
        }
        return res;
    }
}