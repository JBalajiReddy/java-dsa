class DetectSquares {
    Map<Integer, Map<Integer, Integer>> pntCnt;
    public DetectSquares() {
        pntCnt = new HashMap<>();
    }
    
    public void add(int[] point) {
        int x = point[0], y = point[1];
        pntCnt.putIfAbsent(x, new HashMap<>());
        // Increment the count for the specific Y in that X-bucket
        pntCnt.get(x).put(y, pntCnt.get(x).getOrDefault(y, 0) + 1);
    }
    
    public int count(int[] point) {
        int x1 = point[0], y1 = point[1];
        int totalSq = 0;
        if (!pntCnt.containsKey(x1)) return 0;
        for (Map.Entry<Integer, Integer> entry : pntCnt.get(x1).entrySet()) {
            int y2 = entry.getKey();
            int cntY2 = entry.getValue();
            if (y1 == y2) continue;
            int d = Math.abs(y1 - y2);
            totalSq += countSq(x1, y1, x1 + d, y1, y2, cntY2);
            totalSq += countSq(x1, y1, x1 - d, y1, y2, cntY2);
        }
        return totalSq;
    }

    private int countSq(int x1, int y1, int x3, int y1_target, int y2, int cntY2) {
        if (pntCnt.containsKey(x3)) {
            Map<Integer, Integer> x3Points = pntCnt.get(x3);
            return x3Points.getOrDefault(y1, 0) * x3Points.getOrDefault(y2, 0) * cntY2;
        }
        return 0;
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */