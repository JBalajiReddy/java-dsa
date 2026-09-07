class UnionFind {
    // Stores the parent node for each variable (e.g., parent.get("a") = "b")
    private final Map<String, String> parent;

    // Stores ratio of the node relative to its immediate parent: weight[X] = X / parent[X]
    private final Map<String, Double> weight;

    public UnionFind() {
        parent = new HashMap<>();
        weight = new HashMap<>();
    }

    // Registers a variable if it hasn't been added yet
    public void add(String x) {
        if (!parent.containsKey(x)) {
            parent.put(x, x); // Initially, a node is its own parent
            weight.put(x, 1.0); // X / X = 1.0
        }
    }

    // Finds the root of 'x' and flattens the tree (Path Compression)
    public String find(String x) {
        // If x is not its own root, compress path recursively
        if (!x.equals(parent.get(x))) {
            String origParent = parent.get(x); // Store original parent before recursive call

            // Recursively find ultimate root and set it directly as x's parent
            parent.put(x, find(origParent));

            // Update weight to be relative to the ultimate root:
            // (x / ultimateRoot) = (x / origParent) * (origParent / ultimateRoot)
            weight.put(x, weight.get(x) * weight.get(origParent));
        }
        return parent.get(x);
    }

    // Connects two nodes x and y with a ratio value: x / y = value
    public void union(String x, String y, double value) {
        add(x);
        add(y);

        String rootX = find(x);
        String rootY = find(y);

        // If x and y belong to different components, connect their roots
        if (!rootX.equals(rootY)) {
            // Make rootY the parent of rootX
            parent.put(rootX, rootY);

            // Derive required weight for rootX: rootX / rootY
            // From x / y = value  =>  (weightX * rootX) / (weightY * rootY) = value
            // => rootX / rootY = value * weightY / weightX
            weight.put(rootX, value * weight.get(y) / weight.get(x));
        }
    }

    // Evaluates the ratio query x / y
    public double getRatio(String x, String y) {
        // If either variable is unknown or they aren't in the same connected component
        if (!parent.containsKey(x) || !parent.containsKey(y) || !find(x).equals(find(y))) {
            return -1.0;
        }

        // Since find(x) flattens both trees, both weight.get(x) and weight.get(y) 
        // are relative to the shared root R: (x / R) / (y / R) = x / y
        return weight.get(x) / weight.get(y);
    }
}

class Solution {
    public double[] calcEquation(
            List<List<String>> equations, double[] values, List<List<String>> queries) {

        UnionFind uf = new UnionFind();

        // Step 1: Build the Disjoint Set graph using input equations
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String a = equation.get(0);
            String b = equation.get(1);

            // Process equation a / b = values[i]
            uf.union(a, b, values[i]);
        }

        // Step 2: Answer each query using the UnionFind structure
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String a = query.get(0);
            String b = query.get(1);

            // Get calculated ratio for a / b
            result[i] = uf.getRatio(a, b);
        }

        return result;
    }
}