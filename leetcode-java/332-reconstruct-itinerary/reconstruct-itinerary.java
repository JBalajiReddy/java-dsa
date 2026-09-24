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

class Solution_Backtracking {
    Map<String, List<String>> graph = new HashMap();
    List<String> result = new ArrayList();
    int numTickets = 0;

    public List<String> findItinerary(List<List<String>> tickets) {
        numTickets = tickets.size();
        for (List<String> ticket : tickets) {
            graph.put(ticket.get(0), new ArrayList());
        }
        for (List<String> ticket : tickets) {
            graph.get(ticket.get(0)).add(ticket.get(1));
        }
        for (Map.Entry<String, List<String>> edges : graph.entrySet()) {
            Collections.sort(edges.getValue());
        }

        List<String> path = new ArrayList();
        DFS("JFK", path);

        return result;
    }

    public boolean DFS(String fromAirport, List<String> path) {
        path.add(fromAirport);
        if (path.size() == numTickets + 1) {
            result = path;
            return true;
        }

        List<String> neighbors = graph.get(fromAirport);
        for (int i = 0; neighbors != null && i < neighbors.size(); i++) {
            String toAirport = neighbors.get(i);
            neighbors.remove(toAirport);
            if (DFS(toAirport, path)) {
                return true;
            }
            neighbors.add(i, toAirport);
        }
        path.remove(path.size() - 1);
        return false;
    }
}