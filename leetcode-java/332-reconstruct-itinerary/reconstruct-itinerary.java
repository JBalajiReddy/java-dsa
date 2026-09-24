class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        // Step 1: Build graph with PriorityQueue to ensure lexicographical order
        Map<String, PriorityQueue<String>> adj = new HashMap<>();
        for (List<String> ticket : tickets) {
            adj.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            adj.get(ticket.get(0)).offer(ticket.get(1));
        }

        List<String> res = new ArrayList<>();
        
        // Step 2: Post-order Eulerian Path Traversal (Hierholzer's Algorithm)
        dfs("JFK", res, adj);
        
        // Step 3: Reverse post-order result to get correct start-to-finish itinerary
        Collections.reverse(res);
        return res;
    }

    private void dfs(String node, List<String> res, Map<String, PriorityQueue<String>> adj) {
        PriorityQueue<String> pq = adj.get(node);
        
        // Destructively consume edges in lexicographical order
        while (pq != null && !pq.isEmpty()) {
            String nextDestination = pq.poll(); // Consume ticket
            dfs(nextDestination, res, adj);
        }
        
        // Post-Order Step: Add node after exploring all outgoing flights
        res.add(node);
    }
}