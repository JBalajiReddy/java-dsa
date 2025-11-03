class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0)
            return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();
        for (String word : strs) {
            char[] sortChars = word.toCharArray();
            Arrays.sort(sortChars);
            String sortedW = new String(sortChars);

            map.computeIfAbsent(sortedW, k -> new ArrayList<>()).add(word);

            //equi to the following
            // if (!map.containsKey(sortedW)) {
            //     map.put(sortedW, new ArrayList<>());
            // }
            // map.get(sortedW).add(word);

        }

        return new ArrayList<>(map.values());
    }
}