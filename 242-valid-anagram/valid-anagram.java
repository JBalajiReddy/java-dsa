class Solution {
    public boolean isAnagram(String s, String t) {
        char[] c1 = s.toCharArray();
        Arrays.sort(c1);
        char[] c2 = t.toCharArray();
        Arrays.sort(c2);

        return new String(c1).equals(new String(c2));
    }
}

// == compares references, not content, when used with objects (like String)

// So even if the strings contain the same characters in the same order, new String(c1) == new String(c2) will likely return false because they’re different objects in memory