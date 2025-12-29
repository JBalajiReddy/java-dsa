class Solution {
    Map<String, List<String>> mp;
    Set<String> failedMemo;
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        mp = new HashMap<>();
        failedMemo = new HashSet<>();

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
            if (failedMemo.contains(nextRow))
                return false;
            if (solve(nextRow, "", 0))
                return true;
            failedMemo.add(nextRow);
            return false;
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


// class Solution {
//     public boolean pyramidTransition(String bottom, List<String> allowed) {
//         Map<String, List<Character>> map = new HashMap<>();
//         for(String s: allowed){
//             String key = s.substring(0,2); char val = s.charAt(2);
//             if(!map.containsKey(key)){
//                 map.put(key, new ArrayList<>());
//             } 
//             map.get(key).add(val);
//         }
//         StringBuilder sb = new StringBuilder();
//         return backtrack(bottom, map, sb, 0);
//     }
//     public boolean backtrack(String bottom, Map<String, List<Character>> map, StringBuilder sb, int idx){
//         if(bottom.length() == 1) return true;
//         if(sb.length() == bottom.length()-1){
//             return backtrack(sb.toString(), map, new StringBuilder(), 0);
//         }
//         String key = bottom.substring(idx, idx+2);
//         if(!map.containsKey(key)){
//             return false;
//         }
//         for(char ch: map.get(key)){
//             sb.append(ch);
//             if(backtrack(bottom, map, sb, idx+1)){
//                 return true;
//             }
//             sb.deleteCharAt(idx);
//         }
//         return false;
//     }
// }