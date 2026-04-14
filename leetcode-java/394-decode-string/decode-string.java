class Solution {
    public String decodeString(String s) {
        Stack<Integer> numSt = new Stack<>();
        Stack<String> stringSt = new Stack<>();
        StringBuilder currString = new StringBuilder();
        int d = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                d = (d * 10) + (ch - '0'); //building num
            } else if (ch =='[') {
                numSt.push(d);
                stringSt.push(currString.toString());

                //reset current context
                d = 0;
                currString = new StringBuilder();
            } else if (ch == ']') {
                int k = numSt.pop();
                String prev = stringSt.pop();
                String repeated = currString.toString().repeat(k);
                currString = new StringBuilder(prev).append(repeated);
            } else {
                currString.append(ch);
            }
        }
        return currString.toString();
    }
}