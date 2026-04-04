class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int r = mat.length;
        int c = mat[0].length;
        int[][] res = new int[r][c];
        Map<Integer, PriorityQueue<Integer>> mp = new HashMap<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mp.computeIfAbsent(j - i, k -> new PriorityQueue<>()).add(mat[i][j]);
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int ele = mp.get(j - i).poll();
                res[i][j] = ele;
            }
        }

        return res;
    }
}