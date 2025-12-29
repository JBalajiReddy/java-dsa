class Solution {
    Map<String, List<String>> mp;
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        mp = new HashMap<>();

        for (String str : allowed) {
            String lr = str.substring(0, 2);
            String t = str.substring(2);
            mp.putIfAbsent(lr, new ArrayList<>());
            mp.get(lr).add(t);
        }

        return solve(bottom, "", 0);
    }

    private boolean solve(String currRow, String nextRow, int idx) {
        if (currRow.length() == 1)
            return true;
        if (nextRow.length() == currRow.length() - 1) {
            return solve(nextRow, "", 0);
        }

        String key = currRow.substring(idx, idx + 2);
        if (!mp.containsKey(key)) {
            return false;
        }
        for (String top : mp.get(key)) {
            if (solve(currRow, nextRow + top, idx + 1))
                return true;
        }
        return false;
    }
}