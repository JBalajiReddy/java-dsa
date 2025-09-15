class DisjointSet {
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();
    DisjointSet(int n) {
        for (int i = 0; i < n; i++) {
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }

    public int findUltParent(int n) {
        if (parent.get(n) == n) return n;
        int ulp = findUltParent(parent.get(n));
        parent.set(n, ulp);
        return parent.get(n); //updated ult parent
    }

    public void unionByRank(int u, int v) {
        int ultp_u = findUltParent(u);
        int ultp_v = findUltParent(v);
        if (ultp_u == ultp_v) return;

        if (rank.get(ultp_u) > rank.get(ultp_v)) {
            parent.set(ultp_v, ultp_u);
        } else if (rank.get(ultp_u) < rank.get(ultp_v)) {
            parent.set(ultp_u, ultp_v);
        } else {
            parent.set(ultp_v, ultp_u);
            int rankU = rank.get(ultp_u);
            rank.set(ultp_u, rankU + 1);
        }
    }

    public void unionBySize(int u, int v) {
        int ultp_u = findUltParent(u);
        int ultp_v = findUltParent(v);
        if (ultp_u == ultp_v) return;

        if (size.get(ultp_u) > size.get(ultp_v)) {
            parent.set(ultp_v, ultp_u);
            size.set(ultp_v, size.get(ultp_v) + size.get(ultp_u));
        } else if (size.get(ultp_u) < size.get(ultp_v)) {
            parent.set(ultp_u, ultp_v);
            size.set(ultp_u, size.get(ultp_v) + size.get(ultp_u));
        } else {
            parent.set(ultp_v, ultp_u);
            size.set(ultp_u, size.get(ultp_v) + size.get(ultp_u));
        }
    }
}


class Solution {
    public int makeConnected(int n, int[][] connections) {
        DisjointSet ds = new DisjointSet(n);
        int extraEdges = 0;
        for (int c[] : connections) {
            int u = c[0], v = c[1];
            if (ds.findUltParent(u) == ds.findUltParent(v)) extraEdges++; //belong to same comp
            else {
                ds.unionBySize(u, v);
            }
        }

        int comp_cnt = 0;
        for (int i = 0; i < n; i++) {
            if (ds.parent.get(i) == i) comp_cnt++;
        }

        int ans = comp_cnt - 1;
        return extraEdges >= ans ? ans : -1;
    }
}