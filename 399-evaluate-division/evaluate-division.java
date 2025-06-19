class Solution {
    class Neighbor {
        String v;
        double w;

        Neighbor(String v, double w) {
            this.v = v;
            this.w = w;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Neighbor>> adj = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            List<String> edge = equations.get(i);
            List<Neighbor> nbrs1 = adj.getOrDefault(edge.get(0), new ArrayList<>());
            //forward a --> b with weight v[i]
            nbrs1.add(new Neighbor(edge.get(1), values[i]));
            adj.put(edge.get(0), nbrs1);
            List<Neighbor> nbrs2 = adj.getOrDefault(edge.get(1), new ArrayList<>());
            //backward b --> with weight 1 / v[i]
            nbrs2.add(new Neighbor(edge.get(0), 1 / values[i]));
            adj.put(edge.get(1), nbrs2);
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> q = queries.get(i);
            if (adj.containsKey(q.get(0)) && adj.containsKey(q.get(1))) 
              res[i] = solve(q.get(0), q.get(1), 1.0, new HashSet<>(), adj);
            else res[i] = -1.0;
        }
        return res;
    }

    private double solve (String sNode, String eNode, double res, Set<String> visited, Map<String, List<Neighbor>> adj) {
        if (visited.contains(sNode)) return -1.0;
        if (sNode.equals(eNode)) return res;
        visited.add(sNode);

        for (Neighbor nbr : adj.getOrDefault(sNode, new ArrayList<>())) {
            double val = solve(nbr.v, eNode, res * nbr.w, visited, adj);
            if (val != -1) return val;
              }
        return -1.0;
     }
}