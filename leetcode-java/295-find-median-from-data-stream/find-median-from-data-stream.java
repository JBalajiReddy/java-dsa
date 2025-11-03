class MedianFinder {

    PriorityQueue<Integer> max;
    PriorityQueue<Integer> min;

    public MedianFinder() {
        max = new PriorityQueue<>(Comparator.reverseOrder());
        min = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // 1. Add the new number to the max-heap (smaller half).
        // This is a temporary step; we might move it to the min-heap next.
        max.offer(num);

        // 2. Move the largest element from the max-heap to the min-heap.
        // This ensures that the partitioning rule is maintained: the largest element
        // of the smaller half is now correctly placed as the smallest in the larger half.
        min.offer(max.poll());

        // 3. Balance the heaps if necessary.
        // If the min-heap (larger half) has more elements than the max-heap (smaller half),
        // move the smallest element from `min` back to `max` to restore balance.
        // This ensures the `max` heap is either equal in size or has one more element.
        if (max.size() < min.size()) {
            max.offer(min.poll());
        }
    }

    public double findMedian() {
        return (max.size() > min.size()) ? max.peek() : (max.peek() + min.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */