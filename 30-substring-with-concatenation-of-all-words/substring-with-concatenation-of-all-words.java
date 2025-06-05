class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> resultIndices = new ArrayList<>();

        if (words.length == 0 || s.length() == 0) {
            return resultIndices;
        }

        int wordLength = words[0].length();
        int totalWords = words.length;
        int stringLength = s.length();

        HashMap<String, Integer> wordCountMap = new HashMap<>();
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        for (int offset = 0; offset < wordLength; offset++) {
            HashMap<String, Integer> currentCountMap = new HashMap<>();
            int startIndex = offset;
            int matchedWordsCount = 0;

            // Iterate through the string in chunks of wordLength
            for (int endIndex = offset; endIndex + wordLength <= stringLength; endIndex += wordLength) {
                String currentWord = s.substring(endIndex, endIndex + wordLength);

                if (wordCountMap.containsKey(currentWord)) {
                    currentCountMap.put(currentWord, currentCountMap.getOrDefault(currentWord, 0) + 1);
                    matchedWordsCount++;

                    // If the count of the current word exceeds the expected count, move the start index
                    while (currentCountMap.get(currentWord) > wordCountMap.get(currentWord)) {
                        String startWord = s.substring(startIndex, startIndex + wordLength);
                        currentCountMap.put(startWord, currentCountMap.get(startWord) - 1);
                        startIndex += wordLength;
                        matchedWordsCount--;
                    }

                    // If we have matched all words, add the start index to the result
                    if (matchedWordsCount == totalWords) {
                        resultIndices.add(startIndex);
                    }
                } else {
                    // Reset if the current word is not valid
                    matchedWordsCount = 0;
                    startIndex = endIndex + wordLength;
                    currentCountMap.clear();
                }
            }
        }
        return resultIndices;
    }
}