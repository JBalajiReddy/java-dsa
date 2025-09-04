class Solution {
    class Pair {
        int n1;
        int n2;
        double f;

        Pair(int n1, int n2) {
            this.n1 = n1;
            this.n2 = n2;
            f = (double) n1 / n2;
        }
    }

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        if (n == 2 && k == 1)
            return new int[] { arr[0], arr[1] };
        List<Pair> ls = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ls.add(new Pair(arr[i], arr[j]));
            }
        }

        ls.sort(Comparator.comparingDouble(p -> p.f));
        Pair resultPair = ls.get(k - 1);
        int num1 = resultPair.n1;
        int num2 = resultPair.n2;
        return new int[] { num1, num2 };
    }
}