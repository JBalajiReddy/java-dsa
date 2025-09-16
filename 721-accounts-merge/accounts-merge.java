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
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DSU dsu = new DSU(n);
        Map<String, Integer> mp = new HashMap<>(); //mail, node
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                if (!mp.containsKey(mail)) {
                    mp.put(mail, i);
                } else {
                    dsu.unionBySize(i, mp.get(mail)); 
                }
            }
        }

        ArrayList<String>[] mergedMail = new ArrayList[n];
        for (int i = 0; i < n; i++) mergedMail[i] = new ArrayList<String>();

        for (Map.Entry<String, Integer> entry : mp.entrySet()) {
            String mail = entry.getKey();
            int node = dsu.findULTParent(entry.getValue());
            mergedMail[node].add(mail);
        }

        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (mergedMail[i].size() == 0) continue;
            Collections.sort(mergedMail[i]);
            List<String> tmp = new ArrayList<>();
            tmp.add(accounts.get(i).get(0)); //name
            for (String itr : mergedMail[i]) {
                tmp.add(itr);
            }

            res.add(tmp);
        }

        return res;
    }
}