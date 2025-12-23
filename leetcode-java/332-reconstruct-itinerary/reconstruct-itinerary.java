class Solution {
    Map<String, PriorityQueue<String>> graph;
    ArrayList<String> res;
    int n;
    public List<String> findItinerary(List<List<String>> tickets) {
        n = tickets.size();
        graph = new HashMap<>();
        for (List<String> ls : tickets) {
            String start = ls.get(0);
            String end = ls.get(1);
            graph.putIfAbsent(start, new PriorityQueue<>());
            graph.get(start).offer(end);
        }
        res = new ArrayList<>();
        dfs("JFK");
        Collections.reverse(res);
        return res;
    }

    private void dfs(String airport) {
        PriorityQueue<String> des = graph.get(airport);
        while (des != null && !des.isEmpty()) {
            String next = des.poll();
            dfs(next);
        }
        res.add(airport);
    }
}