class Solution {
    int MAX = Integer.MAX_VALUE;

    public int minCost(int[][] grid, int K) {
        int n = grid.length, m = grid[0].length;
        // Assumption: Grid values are between 0 and 10000.
        int range = 10000; 

        // dp[p][i][j] stores the minimum cost to reach the bottom-right from (i, j)
        // using exactly p "teleport" charges.
        int[][][] dp = new int[K+1][n][m];
        
        // Initialize DP table with MAX (infinity)
        for(int p=0; p<=K; p++){
            for(int i=0; i<n; i++){
                Arrays.fill(dp[p][i], MAX);
            }
        }

        // bestPrefix[v] will store the minimum cost found in the PREVIOUS layer (p-1)
        // starting from any cell with value <= v.
        // This optimizes the jump calculation from O(N*M) to O(1).
        int[] bestPrefix = new int[range+1];
        Arrays.fill(bestPrefix, MAX);

        // Iterate through each layer of allowed jumps (0 to K)
        for(int p=0; p<=K; p++){
            
            // bestCurrent[v] tracks the min cost for the CURRENT layer p 
            // for cells with exact value v. Used to build bestPrefix for the next layer.
            int[] bestCurrent = new int[range+1];
            Arrays.fill(bestCurrent, MAX);

            // Traverse grid bottom-up (from target to start)
            for(int i=n-1; i>=0; i--){
                for(int j=m-1; j>=0; j--){
                    
                    // Base Case: We are at the target. Cost is 0.
                    if(i==n-1 && j==m-1){
                        dp[p][i][j] = 0;
                    } else {
                        int ans = MAX;

                        // --- OPTION 1: Physical Walk (Down or Right) ---
                        // Move Down: Cost is value of cell (i+1, j) + remaining path cost
                        if(i+1<n){
                            ans = Math.min(ans, grid[i+1][j] + dp[p][i+1][j]);
                        }
                        // Move Right: Cost is value of cell (i, j+1) + remaining path cost
                        if(j+1<m){
                            ans = Math.min(ans, grid[i][j+1] + dp[p][i][j+1]);
                        }

                        // --- OPTION 2: Teleport/Jump (consumes 1 charge) ---
                        // If we have used at least 1 charge (p > 0), we could have arrived here via a jump.
                        // We check the best cost from the previous layer (p-1) for any destination 
                        // cell (x,y) where grid[x][y] <= grid[i][j].
                        if(p>0){
                            ans = Math.min(ans, bestPrefix[grid[i][j]]);
                        }

                        dp[p][i][j] = ans;
                    }
                    
                    // Update bestCurrent for the specific value of the current cell.
                    // This records that we found a path with cost dp[p][i][j] starting at a cell with value grid[i][j].
                    bestCurrent[grid[i][j]] = Math.min(bestCurrent[grid[i][j]], dp[p][i][j]);
                }
            }

            // Prepare bestPrefix for the NEXT iteration (p+1).
            // We compute a running minimum (prefix min) so that bestPrefix[v] 
            // covers all values from 0 to v.
            Arrays.fill(bestPrefix, MAX);
            bestPrefix[0] = bestCurrent[0];
            for(int r=1; r<=range; r++){
                bestPrefix[r] = Math.min(bestPrefix[r-1], bestCurrent[r]);
            }
        }
        
        // Return min cost starting from (0,0) with K jumps allowed.
        return dp[K][0][0];
    }
}