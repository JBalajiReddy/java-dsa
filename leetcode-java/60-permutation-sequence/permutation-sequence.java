class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int fact = 1;
        for (int i = 1; i < n; i++) {
            fact = fact * i;
            nums.add(i);
        }

        nums.add(n);
        k = k - 1;

        StringBuilder sb = new StringBuilder();
        while (true) {
            int idx = k / fact; //chooses the correct block
            sb.append(nums.get(idx));
            nums.remove(idx);
            if (nums.size() == 0 || nums.isEmpty())
                break;

            k = k % fact; //updates k for next loop
            fact = fact / nums.size(); //updates block size for next iter
        }

        return sb.toString();
    }
}