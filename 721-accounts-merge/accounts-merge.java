// Disjoint Set Union (DSU) or Union-Find data structure implementation.
class DSU {
    // Stores the size of each set. Used for union-by-size optimization.
    List<Integer> size = new ArrayList<>();
    // Stores the parent of each element in the set.
    List<Integer> parent = new ArrayList<>();

    // Constructor to initialize n+1 disjoint sets (from 0 to n).
    DSU(int n) {
        for (int i = 0; i <= n; i++) {
            size.add(1);      // Each set initially has a size of 1.
            parent.add(i);    // Each element is its own parent initially.
        }
    }

    // Finds the ultimate parent (root) of the set containing element 'n'.
    // Implements path compression for optimization.
    int findULTParent(int n) {
        // Base case: if 'n' is its own parent, it's the root.
        if (parent.get(n) == n)
            return n;
        // Recursively find the root.
        int ultp = findULTParent(parent.get(n));
        // Path compression: set the parent of 'n' directly to the root.
        parent.set(n, ultp);
        return parent.get(n);
    }

    // Merges the sets containing elements 'u' and 'v'.
    // Implements union-by-size to keep the tree flat.
    void unionBySize(int u, int v) {
        int ult_u = findULTParent(u);
        int ult_v = findULTParent(v);
        // If they are already in the same set, do nothing.
        if (ult_u == ult_v)
            return;
        // Attach the smaller tree to the root of the larger tree.
        if (size.get(ult_u) > size.get(ult_v)) {
            parent.set(ult_v, ult_u);
            size.set(ult_u, size.get(ult_u) + size.get(ult_v));
        } else if (size.get(ult_u) < size.get(ult_v)) {
            parent.set(ult_u, ult_v);
            size.set(ult_v, size.get(ult_u) + size.get(ult_v));
        } else { // If sizes are equal, attach one to the other and increment size.
            parent.set(ult_v, ult_u);
            size.set(ult_u, size.get(ult_u) + size.get(ult_v));
        }
    }
}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        // Each account is initially its own set.
        DSU dsu = new DSU(n);
        // Map to store the first account index seen for each unique email.
        Map<String, Integer> mp = new HashMap<>(); // mail -> node index

        // STEP 1: Iterate through all accounts and union sets if they share an email.
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                // If this is the first time we see this email, map it to the current account index.
                if (!mp.containsKey(mail)) {
                    mp.put(mail, i);
                } else {
                    // If we've seen this email before, it means the current account 'i' and the
                    // previously seen account 'mp.get(mail)' belong to the same person.
                    // So, we merge (union) their sets.
                    dsu.unionBySize(i, mp.get(mail));
                }
            }
        }

        // STEP 2: Group all emails by their ultimate parent (root) account.
        // Create a list for each account to hold the merged emails.
        ArrayList<String>[] mergedMail = new ArrayList[n];
        for (int i = 0; i < n; i++) mergedMail[i] = new ArrayList<String>();

        // Iterate through the unique emails we found.
        for (Map.Entry<String, Integer> entry : mp.entrySet()) {
            String mail = entry.getKey();
            // Find the ultimate parent (root) of the set this email belongs to.
            int node = dsu.findULTParent(entry.getValue());
            // Add the email to the list corresponding to its root account.
            mergedMail[node].add(mail);
        }

        // STEP 3: Format the final merged accounts.
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // If a list is empty, it means this account was merged into another one and is not a root.
            if (mergedMail[i].size() == 0) continue;
            // Sort the emails alphabetically as required.
            Collections.sort(mergedMail[i]);
            // Create the final list for the merged account.
            List<String> tmp = new ArrayList<>();
            // The name is taken from the root account.
            tmp.add(accounts.get(i).get(0));
            // Add all the sorted, unique emails.
            for (String itr : mergedMail[i]) {
                tmp.add(itr);
            }
            // Add the fully merged account to the result.
            res.add(tmp);
        }

        return res;
    }
}