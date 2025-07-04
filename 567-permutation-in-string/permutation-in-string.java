class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (m < n)
            return false;
        int map1[] = new int[26];
        int map2[] = new int[26];
        for (int i = 0; i < n; i++) {
            map1[s1.charAt(i) - 'a']++;
            map2[s2.charAt(i) - 'a']++;
        }
        //initial window   
        if (isMatched(map1, map2)) {
            return true;
        }

        //next windows
        for (int i = 1; i <= m - n; i++) {
            map2[s2.charAt(i - 1) - 'a']--; // del
            map2[s2.charAt(i + n - 1) - 'a']++;// add
            if (isMatched(map1, map2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMatched(int map1[], int map2[]) {
        for (int i = 0; i < 26; i++) {
            if (map1[i] != map2[i])
                return false;
        }
        return true;
    }
}

// class Solution {
//     public boolean checkInclusion(String s1, String s2) {
//         int[] freq = new int[26];
//         for (int i = 0; i < s1.length(); i++)
//             freq[s1.charAt(i) - 'a']++;

//         int winSize = s1.length();
//         for (int i = 0; i < s2.length(); i++) {
//             int[] winFreq = new int[26];
//             int winIdx = 0, idx = i;

//             while (winIdx < winSize && idx < s2.length()) {
//                 winFreq[s2.charAt(idx) - 'a']++;
//                 winIdx++;
//                 idx++;
//             }

//             if (isSameFreq(freq, winFreq))
//                 return true;
//         }
//         return false;
//     }

//     private boolean isSameFreq(int[] f1, int[] f2) {
//         for (int i = 0; i < 26; i++) {
//             if (f1[i] != f2[i])
//                 return false;
//         }

//         return true;
//     }
// }