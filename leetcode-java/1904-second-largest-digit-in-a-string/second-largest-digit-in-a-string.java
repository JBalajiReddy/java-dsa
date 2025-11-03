class Solution {
    public int secondHighest(String s) {
        TreeSet<Integer> ts = new TreeSet<>();
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch))
                ts.add(ch - '0');
        }
        if (ts.size() < 2)
            return -1;
        ts.pollLast();
        return ts.last();
    }
}