class Solution {
    public int thirdMax(int[] nums) {
        TreeSet<Integer> ts = new TreeSet<>();
        for (int num : nums) {
            ts.add(num);
        }

        if (ts.size() < 3) {
            return ts.last(); 
        }

        //remove top 2
        ts.pollLast(); 
        ts.pollLast(); 

        return ts.last();
    }
}