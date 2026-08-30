class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Track the number of prerequisites remaining for each course.
        // indegree[i] store how many courses must be completed BEFORE course i.
        int[] indegree = new int[numCourses];
        
        // Step 2: Build an Adjacency List to represent the directed graph.
        // adj.get(u) will contain a list of all courses that depend on course u.
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // Step 3: Populate the Adjacency List and calculate In-Degrees.
        // In this representation, pre[0] -> pre[1] means course pre[0] unlocks course pre[1].
        for (int[] pre : prerequisites) {
            int u = pre[0]; // Prerequisite course
            int v = pre[1]; // Dependent course
            
            indegree[v]++;  // Course v requires one more prerequisite to be cleared
            adj.get(u).add(v); // Store the dependency u -> v
        }

        // Step 4: Initialize a Queue for BFS traversal (Kahn's Algorithm).
        // Push all courses that have 0 prerequisites (ready to be taken immediately).
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // Counter to track total courses successfully completed
        int finish = 0;

        // Step 5: Process courses in BFS order.
        while (!q.isEmpty()) {
            // Take a course off the queue (its prerequisites are fully satisfied)
            int node = q.poll();
            finish++; // Increment completed course count

            // Iterate over all courses that depend on the current course
            for (int nei : adj.get(node)) {
                indegree[nei]--; // Satisfy one prerequisite requirement for course 'nei'

                // If all prerequisites for course 'nei' are met, it is now ready to take
                if (indegree[nei] == 0) {
                    q.add(nei);
                }
            }
        }

        // Step 6: Check for cycles.
        // If finish == numCourses, all courses were taken (Valid DAG).
        // If finish < numCourses, a cycle existed preventing some courses from reaching indegree == 0.
        return finish == numCourses;
    }
}

class Solution_DFS_CycleCheck {
    // Map each course to its prerequisites
    private Map<Integer, List<Integer>> preMap = new HashMap<>();
    // Store all courses along the current DFS path
    private Set<Integer> visiting = new HashSet<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < numCourses; i++) {
            preMap.put(i, new ArrayList<>());
        }
        for (int[] prereq : prerequisites) {
            preMap.get(prereq[0]).add(prereq[1]);
        }

        for (int c = 0; c < numCourses; c++) {
            if (!dfs(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int crs) {
        if (visiting.contains(crs)) {
            // Cycle detected
            return false;
        }
        if (preMap.get(crs).isEmpty()) {
            return true;
        }

        visiting.add(crs);
        for (int pre : preMap.get(crs)) {
            if (!dfs(pre)) {
                return false;
            }
        }
        visiting.remove(crs);
        preMap.put(crs, new ArrayList<>());
        return true;
    }
}