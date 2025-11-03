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
    public int removeStones(int[][] stones) {
        int n = stones.length;
        int maxRow = 0, maxCol = 0;
        for (int i = 0; i < n; i++) {
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }

        DSU dsu = new DSU(maxRow + maxCol + 1);
        Map<Integer, Integer> stoneMp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int nodeRow = stones[i][0];
            int nodeCol = stones[i][1] + maxRow + 1;
            dsu.unionBySize(nodeRow, nodeCol);
            stoneMp.put(nodeRow, 1);
            stoneMp.put(nodeCol, 1);
        }

        int cnt = 0;
        for (Map.Entry<Integer, Integer> entry : stoneMp.entrySet()) {
            if (dsu.findULTParent(entry.getKey()) == entry.getKey()) cnt++;
        }

        return n - cnt;
    }
}