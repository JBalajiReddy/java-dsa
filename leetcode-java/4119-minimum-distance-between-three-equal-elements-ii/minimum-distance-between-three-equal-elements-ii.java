class Solution {
    public int minimumDistance(int[] nums) {
        if (nums.length < 3) return -1;
        Map<Integer, ArrayList<Integer>> mp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            mp.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }


        Map<Integer, Integer> minDist = new HashMap<>();
        boolean flag = false;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : mp.entrySet()) {
            ArrayList<Integer> tmp = entry.getValue();
            if (tmp.size() >= 3) {
                flag = true;
            }
            int num = entry.getKey();
            int i = 0;
            int d = 1000_000_000;
            while (i < tmp.size() - 2) {
                int j = i + 1;
                int k = j + 1;
                int dist = Math.abs(tmp.get(i) - tmp.get(j)) + Math.abs(tmp.get(j) - tmp.get(k)) + Math.abs(tmp.get(k) - tmp.get(i));

                if (dist < d) {
                    d = dist;
                }
                minDist.put(num, d);
                i++;
            }
        }

        int minVal = 1000_000_000;
        for (int n : minDist.values()) {
            minVal = Math.min(minVal, n);
        } 
        return flag ? minVal : -1;
    }
}