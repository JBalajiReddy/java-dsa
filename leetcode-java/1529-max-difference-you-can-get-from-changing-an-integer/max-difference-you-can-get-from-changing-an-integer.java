class Solution {
    public int maxDiff(int num) {
        String str = Integer.toString(num);

        String num1 = new String(str);
        String num2 = new String(str);

        //create the max number
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '9') {
                String c = str.charAt(i) + "";
                num1 = str.replace(c, "9");
                break;
            }
        }

        //special case, can return earlier 
        if (str.charAt(0) != '1') {
            String c = str.charAt(0) + "";
            num2 = str.replace(c, "1");
            return Integer.parseInt(num1) - Integer.parseInt(num2);
        }

        //create the min number 
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != '0' && str.charAt(i) != '1') {
                String c = str.charAt(i) + "";
                num2 = str.replace(c, "0");
                break;
            }
        }

        return Integer.parseInt(num1) - Integer.parseInt(num2);

    }
}