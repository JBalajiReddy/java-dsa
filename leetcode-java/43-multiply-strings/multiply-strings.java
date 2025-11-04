class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";

        int n1 = num1.length(), n2 = num2.length();
        int[] res = new int[n1 + n2];

        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';

                int mul = d1 * d2;

                int p2 = i + j + 1; //pos for one's digit
                int p1 = i + j; //pos for ten's digit

                int sum = mul + res[p2];

                res[p2] = sum % 10; //one's digit of sum at p2
                res[p1] += sum / 10; //ten's digit (carry) of sum added at p1
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int digit : res) {
            // Skip leading zeros
            if (!(sb.length() == 0 && digit == 0)) {
                sb.append(digit);
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}