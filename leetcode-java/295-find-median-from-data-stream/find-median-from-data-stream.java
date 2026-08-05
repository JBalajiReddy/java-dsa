class MedianFinder {
    PriorityQueue<Integer> minH;
    PriorityQueue<Integer> maxH;

    public MedianFinder() {
        minH = new PriorityQueue<>(); // for 2nd half
        maxH = new PriorityQueue<>(Collections.reverseOrder()); // for 1st half
    }

    public void addNum(int num) {
        // 1. Direct routing (1 heap op in most branch paths)
        if (maxH.isEmpty() || num <= maxH.peek()) {
            maxH.offer(num);
        } else {
            minH.offer(num);
        }

        // 2. Rebalance sizes if off by more than 1 (1 push + 1 pop = 2 heap ops)
        if (maxH.size() > minH.size() + 1) {
            minH.offer(maxH.poll());
        } else if (minH.size() > maxH.size()) {
            maxH.offer(minH.poll());
        }
    }

    public double findMedian() {
        return (maxH.size() > minH.size()) ? maxH.peek() : (maxH.peek() + minH.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */