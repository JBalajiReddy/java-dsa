class MedianFinder {

    PriorityQueue<Integer> minH;
    PriorityQueue<Integer> maxH;

    public MedianFinder() {
        minH = new PriorityQueue<>(); //for 2nd half
        maxH = new PriorityQueue<>(Collections.reverseOrder());  //for 1st half
    }
    
    public void addNum(int num) {   
        maxH.offer(num);
        minH.offer(maxH.poll());
        if (maxH.size() < minH.size()) {
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