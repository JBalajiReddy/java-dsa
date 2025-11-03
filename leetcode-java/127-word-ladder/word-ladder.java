class Solution {
    class Pair {
        String w;
        int lvl;
        Pair(String w, int lvl) {
            this.w = w;
            this.lvl = lvl;
        }
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(beginWord, 1));
        set.remove(beginWord);

        while (!q.isEmpty()) {
            Pair p = q.poll();
            String word = p.w;
            int lvl = p.lvl;

            if (word.equals(endWord)) return lvl;

            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char[] replacedWord = word.toCharArray();
                    replacedWord[i] = ch;
                    String newWord = new String(replacedWord);

                    if (set.contains(newWord)) {
                        q.offer(new Pair(newWord, lvl + 1));
                        set.remove(newWord);
                    }
                }
            }
        }

        return 0;
    }
}