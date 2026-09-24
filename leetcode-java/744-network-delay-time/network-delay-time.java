class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Step 1: Build the Adjacency List for the directed graph.
        // Index represents the source node 'u'.
        // Element is a list of pairs: {destination_node 'v', travel_time 'cost'}.
        // Note: Allocated size (n + 1) because nodes are 1-indexed (1 to n).
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        // Populate directed edges (u -> v with weight 'time')
        for (int[] t : times) {
            int u = t[0], v = t[1], cost = t[2];
            adj.get(u).add(new int[] {v, cost});
        }

        // Step 2: Initialize Distance Array with Infinity (1e9).
        // dist[i] tracks the shortest known time for the signal to travel from source 'k' to node 'i'.
        int[] dist = new int[n + 1];
        Arrays.fill(dist, (int) 1e9);
        
        // Distance from starting node 'k' to itself is always 0.
        dist[k] = 0;

        // Step 3: Min-Heap Priority Queue for Dijkstra's Algorithm.
        // Stores elements as {node, accumulated_time_from_k}.
        // Custom comparator (a, b) -> a[1] - b[1] ensures the node reachable in the 
        // SMALLEST accumulated time is always polled first.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {k, 0});

        while (!pq.isEmpty()) {
            int[] state = pq.poll();
            int u = state[0], time = state[1];

            // Gotcha/Optimization: Outdated Path Check.
            // Because Dijkstra pushes updated distances into the PQ rather than updating existing entries,
            // we might poll a node 'u' with a stale (longer) time. If we already found a faster 
            // path to 'u', ignore this duplicate processing.
            if (time > dist[u]) {
                continue;
            }

            // Step 4: Edge Relaxation.
            // Traverse all outgoing directed edges from node 'u'.
            for (int[] e : adj.get(u)) {
                int v = e[0], cost = e[1];

                // If traveling through 'u' reaches neighbor 'v' faster than previously recorded,
                // update dist[v] and push the new state to the Min-Heap.
                if (time + cost < dist[v]) {
                    dist[v] = time + cost;
                    pq.offer(new int[] {v, time + cost});
                }
            }
        }

        // Step 5: Aggregate the final result.
        // Signals travel concurrently along outgoing branches. The total time for ALL nodes
        // to receive the signal is the MAXIMUM shortest path time among all nodes 1 to n.
        int maxDelay = 0;
        for (int i = 1; i <= n; i++) {
            // Gotcha: If any node remains at infinity (1e9), it is unreachable from source 'k'.
            if (dist[i] == (int) 1e9) {
                return -1; 
            }
            maxDelay = Math.max(maxDelay, dist[i]);
        }

        return maxDelay;
    }
}