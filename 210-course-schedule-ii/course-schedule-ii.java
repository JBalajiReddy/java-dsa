class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        //Course        neigh-Courses
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new HashSet<>());
        }

        for (int[] p : prerequisites) {
            int dCourse = p[0];
            int course = p[1];
            //course -> dCourse 
            map.get(course).add(dCourse);
            inDegree[dCourse]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        //order
        List<Integer> ls = new ArrayList<>();
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int head = q.poll();
                if (inDegree[head] == 0)
                    ls.add(head);

                for (int neigh : map.get(head)) {
                    inDegree[neigh]--;
                    if (inDegree[neigh] == 0)
                        q.offer(neigh);
                }
            }
        }

        int i = 0;
        int[] res = new int[ls.size()];
        for (int ele : ls) {
            res[i] = ele;
            i++;
        }

        if (ls.size() != numCourses)
            return new int[] {};
        return res;
    }
}