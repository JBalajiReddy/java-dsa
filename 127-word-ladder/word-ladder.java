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
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(beginWord, 1));
        Set<String> set = new HashSet<>(wordList);
        set.remove(beginWord);

        while (!q.isEmpty()) {
            Pair p = q.poll();
            String word = p.w;
            int lvl = p.lvl;
            if (word.equals(endWord))
                return lvl;

            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char[] replacedArr = word.toCharArray();
                    replacedArr[i] = ch;
                    String newWord = new String(replacedArr);

                    if (set.contains(newWord)) {
                        set.remove(newWord);
                        q.offer(new Pair(newWord, lvl + 1));
                    }
                }
            }
        }
        return 0;
    }
}