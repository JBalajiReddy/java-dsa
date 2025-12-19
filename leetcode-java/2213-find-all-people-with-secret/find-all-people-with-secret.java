class Solution {
    int[] parent, rank;

    int find(int x) {
        if (parent[x] != x) {
            return find(parent[x]);
        }
        return parent[x];
    }

    void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
        } else {
            parent[pa] = pb;
            rank[pb]++;
        }
    }

    void reset(int x) {
        parent[x] = x;
        rank[x] = 0;
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[2], b[2])); //sort based on time
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++)
            parent[i] = i;
        union(0, firstPerson);

        int i = 0, m = meetings.length;
        while (i < m) {
            int time = meetings[i][2];
            List<Integer> ls = new ArrayList<>();
            while (i < m && meetings[i][2] == time) {
                union(meetings[i][0], meetings[i][1]);
                ls.add(meetings[i][0]);
                ls.add(meetings[i][1]);
                i++;
            }

            for (int p : ls) {
                if (find(0) != find(p)) {
                    reset(p);
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int p = 0; p < n; p++) {
            if (find(0) == find(p))
                res.add(p);
        }
        return res;
    }
}