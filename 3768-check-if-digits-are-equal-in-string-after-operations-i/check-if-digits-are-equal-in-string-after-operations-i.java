class Solution {
    public boolean hasSameDigits(String s) {
        List<Integer> ls = new ArrayList<>();
        for (char ch : s.toCharArray()) ls.add(ch - '0');
        while (ls.size() > 2) {
        List<Integer> tmp = new ArrayList<>();
            int i = 0;
            while (i < ls.size() - 1) {
                int a = ls.get(i);
                int b = ls.get(i + 1);
                int c = (a + b) % 10;
                tmp.add(i, c);
                i++;
            }
            ls = tmp;
        }
        return ls.get(0) == ls.get(1);
    }
}