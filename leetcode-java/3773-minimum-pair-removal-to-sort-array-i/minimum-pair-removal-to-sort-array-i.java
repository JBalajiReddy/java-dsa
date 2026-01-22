class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> ls = new ArrayList<>();
        for (int num : nums)
            ls.add(num);
        if (check(ls))
            return 0;
        int cnt = 0;
        while (!check(ls)) {
            int sum = Integer.MAX_VALUE, idx = 0;
            for (int i = 0; i < ls.size() - 1; i++) {
                int currSum = ls.get(i) + ls.get(i + 1);
                if (currSum < sum) {
                    sum = currSum;
                    idx = i;
                }
            }
            int ele = ls.get(idx) + ls.get(idx + 1);
            ls.remove(idx + 1);
            ls.set(idx, ele);
            cnt++;
        }
        return cnt;
    }

    private boolean check(List<Integer> ls) {
        for (int i = 0; i < ls.size() - 1; i++) {
            if (ls.get(i) > ls.get(i + 1))
                return false;
        }
        return true;
    }
}