import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        long maxCost = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            
            // LOGIC: Filter out edges connected to offline nodes immediately.
            // This ensures our traversal graph only contains completely operational routes.
            if (online[u] && online[v]) {
                graph.get(u).add(new int[]{v, cost});
                maxCost = Math.max(maxCost, cost); // Track max edge cost to set our upper bound for binary search
            }
        }
        
        // LOGIC: Guard clause. If the starting line or finish line is broken,
        // no path configuration can ever be valid.
        if (!online[0] || !online[n - 1]) return -1;

        // LOGIC: Binary Search on Answer space setup.
        // We are searching for the optimal bottleneck threshold value.
        long left = 0;
        long right = maxCost;
        long ans = -1;
        
        while (left <= right) {
            // Using safe mid calculation to prevent potential integer overflow
            long mid = left + (right - left) / 2;
            
            // LOGIC: Check if a path exists where EVERY edge is >= mid, and total cost <= k
            if (isValid(n, graph, mid, k)) {
                ans = mid;        // 'mid' works, record it as a potential optimal solution
                left = mid + 1;   // Try to push for a higher/better minimum edge score
            } else {
                right = mid - 1;  // 'mid' is too restrictive or expensive; scale down the threshold
            }
        }
        
        return (int) ans;
    }

    public boolean isValid(int n, List<List<int[]>> graph, long minThreshold, long k) {
        // Track the minimum cumulative cost to reach each node under the current 'minThreshold' constraint
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        
        // Min-Priority Queue tracking: {node_id, cumulative_path_cost}
        // Sorted by cumulative cost so we always explore the overall cheapest path options first
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        
        dist[0] = 0;
        pq.offer(new long[]{0, 0});
        
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int u = (int) curr[0];
            long currentCost = curr[1];
            
            // Because Min-PQ extracts elements by lowest cost, the very first time
            // we extract the destination node (n - 1), we have found its absolute shortest path.
            if (u == n - 1) {
                return currentCost <= k; // Return true only if this optimal path respects our budget k
            }
            
            // OPTIMIZATION (Gotcha): Skip stale, outdated queue elements if we've already 
            // found a cheaper route to node 'u' during a separate loop iteration.
            if (currentCost > dist[u]) continue;
            
            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int edgeCost = edge[1];
                
                // LOGIC: Structural filter. Pretend this edge does not exist if it drops
                // below the minimum score constraint required by our binary search.
                if (edgeCost >= minThreshold) {
                    
                    // Standard Dijkstra relaxation step: is this path cheaper than previously found ones?
                    if (currentCost + edgeCost < dist[v]) {
                        dist[v] = currentCost + edgeCost;
                        pq.offer(new long[]{v, dist[v]});
                    }
                }
            }
        }
        return false; // Destination unreachable under current constraint conditions
    }
}