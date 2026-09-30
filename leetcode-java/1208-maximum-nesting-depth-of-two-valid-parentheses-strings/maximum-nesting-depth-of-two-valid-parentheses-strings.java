class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] res = new int[n];
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char ch = seq.charAt(i);

            if (ch == '(') {
                depth++;
                // Assign based on current depth after entering layer
                res[i] = depth % 2; 
            } else {
                // Assign based on depth before leaving layer
                res[i] = depth % 2; 
                depth--;
            }
        }

        return res;
    }
}