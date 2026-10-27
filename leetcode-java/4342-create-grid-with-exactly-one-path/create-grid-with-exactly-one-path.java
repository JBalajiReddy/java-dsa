class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];

        // L path
        for (int i = 0; i < m - 1; i++) {
            String str = ".";
            for (int j = 0; j < n - 1; j++) {
                str = str + "#";
            }
            grid[i] = str;
        }


        String lastRow = "";
        for (int j = 0; j < n; j++) { 
            lastRow = lastRow + ".";
        }
        grid[m - 1] = lastRow;

        return grid;
    }
}