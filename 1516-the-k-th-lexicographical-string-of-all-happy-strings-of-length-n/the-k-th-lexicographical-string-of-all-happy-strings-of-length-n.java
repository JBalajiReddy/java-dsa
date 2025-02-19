class Solution {
    private int count = 0;
    private String res = "";

    public String getHappyString(int n, int k) {
        backtrack(n, k, "");
        return res;
    }

    public void backtrack(int n, int k, String currStr) {
        if (currStr.length() == n) {
            count++;
            if (count == k) {
                res = currStr;
            }
            return;
        }

        for (char currChar = 'a'; currChar <= 'c'; currChar++) {
            if (currStr.length() == 0 || currStr.charAt(currStr.length() - 1) != currChar) {
                backtrack(n, k, currStr + currChar);
            }
        }
    }
}

// class Solution {
// List<String> list = new ArrayList<>();

// public String getHappyString(int n, int k) {
// String currStr = "";
//if (list.sizw() < k) return "";
// backtrack(n, currStr);
// Collections.sort(list);
// return (k > list.size()) ? list.get(k - 1) : "";
// }

// public void backtrack(int n, String currStr) {
// if (currStr.length() == n) {
// list.add(currStr);
// return;
// }

// for (char currChar = 'a'; currChar <= 'c'; currChar++) {
// if (currStr.length() > 0 && currStr.charAt(currStr.length() - 1) == currChar)
// {
// continue;
// } else {
// backtrack(n, currStr + currChar);
// }
// }
// }
// }