class KthLargest {
    PriorityQueue<Integer> pq;
    int size;
    public KthLargest(int k, int[] nums) {
        this.size = k;
        pq = new PriorityQueue<>((a, b) -> a - b);
        for (int n : nums) {
            pq.offer(n);
            if (pq.size() > k) {
                pq.poll();
            }
        }
    }

    public int add(int val) {
        if (pq.size() <= size) {
            pq.offer(val);
            if (pq.size() > size) {
                pq.poll();
            }
        }

        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */