class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<String>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int start = nums[i];
            while (i + 1 < n && nums[i + 1] == nums[i] + 1) {
                i++;
            }
            if (start != nums[i])
                res.add(start + "->" + nums[i]);
            else
                res.add(""+start);
        }
        return res;
    }
}