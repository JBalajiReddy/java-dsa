class Solution {
    public boolean canFinish(int n, int[][] prerequisites) {
        //n->numCourses
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        for (int[] edge : prerequisites) {
            int a = edge[0];
            int b = edge[1];
            graph.get(b).add(a); //edge from b->a (a depends on b)        
        }

        int[] color = new int[n]; // 0 -> Unvisited, 1 -> Visiting, 2 -> Visited

        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (checkCycle(i, graph, color))
                    return false; //cycle detected
            }
        }
        return true;
    }

    private boolean checkCycle(int node, List<List<Integer>> graph, int[] color) {
        color[node] = 1; //visiting

        for (int nbr : graph.get(node)) {
            if (color[nbr] == 1)
                return true; //cycle

            if (color[nbr] == 2)
                continue; //already visited - ignore

            if (checkCycle(nbr, graph, color))
                return true; //cycle among neighbor
        }

        color[node] = 2; //mark visited
        return false;
    }
}