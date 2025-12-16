class Solution {
    List<Integer>[] tree;
    int[] buyPrice, sellPrice;
    int budget;

    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        this.buyPrice = present;
        this.sellPrice = future;
        this.budget = budget;

        // Initialize adjacency list for the tree structure
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<>();

        // Build the tree (convert 1-based input to 0-based index)
        for (int[] h : hierarchy) {
            int boss = h[0] - 1;
            int emp = h[1] - 1;
            tree[boss].add(emp);
        }

        // Start DFS from the root (node 0)
        int[][] dp = dfs(0);

        // Return dp[budget][0] because the root node has no parent, 
        // so it is impossible for the root to receive a discount (state 1).
        return dp[budget][0];
    }

    public int[][] dfs(int u) {
        // dp[b][0] -> Max profit for subtree at 'u' with budget 'b', IF u's parent did NOT buy u.
        // dp[b][1] -> Max profit for subtree at 'u' with budget 'b', IF u's parent DID buy u.
        // Initially, profit is 0 (base case before processing children).
        int[][] dp = new int[budget + 1][2];

        // 1. PROCESS ALL CHILDREN (Knapsack Merge)
        for (int v : tree[u]) {
            int[][] child = dfs(v); // Recursive call to get child's DP table
            int[][] next = new int[budget + 1][2]; // Temporary table for merging

            // Iterate through all possible total budgets
            for (int b = 0; b <= budget; b++) {
                // Iterate through all possible splits: 
                // 'cb' for the current child, 'b - cb' for previous children
                for (int cb = 0; cb <= b; cb++) {

                    // Case 0: We are building state 0 for node 'u' (Parent didn't buy 'u').
                    // Therefore, 'u' cannot pass a discount to child 'v'.
                    // We combine 'dp' state 0 (previous kids) + 'child' state 0 (current kid).
                    next[b][0] = Math.max(next[b][0],
                            dp[b - cb][0] + child[cb][0]);

                    // Case 1: We are building state 1 for node 'u' (Parent DID buy 'u').
                    // Note: This loop just merges results. The actual buying of 'u' happens later.
                    // Here, we simply accumulate the best potential profits if we WERE to continue down the "discount path".
                    next[b][1] = Math.max(next[b][1],
                            dp[b - cb][1] + child[cb][1]);
                }
            }
            // Update the accumulator with the merged results
            dp = next;
        }

        // 2. MAKE DECISION FOR CURRENT NODE 'u'
        // 'dp' now contains the optimal sum of profits from all children.
        // We now decide whether to buy node 'u' itself or not.
        int[][] ans = new int[budget + 1][2];

        for (int b = 0; b <= budget; b++) {

            // --- SCENARIO 0: Parent of u did NOT buy u ---

            // Option A: Don't buy u.
            // Result is just the profit from children (state 0: children got no discount).
            ans[b][0] = dp[b][0];

            // Option B: Buy u (Full Price).
            // We pay full price because parent didn't buy u.
            if (b >= buyPrice[u]) {
                // Profit = (Sell - Buy) + Children's profit.
                // Important: Since we bought u, children NOW get a discount, so we use dp[...][1].
                ans[b][0] = Math.max(ans[b][0],
                        sellPrice[u] - buyPrice[u] + dp[b - buyPrice[u]][1]);
            }

            // --- SCENARIO 1: Parent of u DID buy u ---

            int discounted = buyPrice[u] / 2;

            // Option A: Don't buy u.
            // Even though we could get u cheap, we choose not to.
            // Since we don't buy u, the discount chain breaks. Children get NO discount (use dp[...][0]).
            ans[b][1] = dp[b][0];

            // Option B: Buy u (Half Price).
            if (b >= discounted) {
                // Profit = (Sell - DiscountedCost) + Children's profit.
                // Since we bought u, children get a discount, so we use dp[...][1].
                ans[b][1] = Math.max(ans[b][1],
                        sellPrice[u] - discounted + dp[b - discounted][1]);
            }
        }

        return ans;
    }
}