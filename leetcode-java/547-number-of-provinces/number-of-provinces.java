class DSU {
    List<Integer> size = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();

    DSU(int n) {
        for (int i = 0; i <= n; i++) {
            size.add(1);
            parent.add(i);
        }
    }

    int findULTParent(int n) {
        if (parent.get(n) == n)
            return n;
        int ultp = findULTParent(parent.get(n));
        parent.set(n, ultp);
        return parent.get(n);
    }

    void unionBySize(int u, int v) {
        int ult_u = findULTParent(u);
        int ult_v = findULTParent(v);
        if (ult_u == ult_v)
            return;
        if (size.get(ult_u) > size.get(ult_v)) {
            parent.set(ult_v, ult_u);
            size.set(ult_u, size.get(ult_u) + size.get(ult_v));
        } else if (size.get(ult_u) < size.get(ult_v)) {
            parent.set(ult_u, ult_v);
            size.set(ult_v, size.get(ult_u) + size.get(ult_v));
        } else {
            parent.set(ult_v, ult_u);
            size.set(ult_u, size.get(ult_u) + size.get(ult_v));
        }
    }
}

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DSU dsu = new DSU(n);
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && isConnected[i][j] == 1) {
                    int u = i + 1;
                    int v = j + 1;
                    if (dsu.findULTParent(u) == dsu.findULTParent(v)) {
                        continue;
                    } else {
                        dsu.unionBySize(u, v);
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (dsu.parent.get(i) == i)
                cnt++;
        }

        return cnt;
    }
}