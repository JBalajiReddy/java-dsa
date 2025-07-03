class Solution {
    public boolean isPalindrome(String s) {
        if (s.isEmpty())
            return true;

        int n = s.length();
        int i = 0;
        int j = n - 1;

        while (i <= j) {
            char a = s.charAt(i);
            char b = s.charAt(j);
            if (!Character.isLetterOrDigit(a))
                i++;
            else if (!Character.isLetterOrDigit(b))
                j--;
            else {
                if (Character.toLowerCase(a) != Character.toLowerCase(b))
                    return false;
                i++;
                j--;
            }
        }
        return true;
    }
}

// public class Solution {
//     public boolean isPalindrome(String s) {
//         String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
//         String rev = new StringBuffer(actual).reverse().toString();
//         return actual.equals(rev);
//     }
// }