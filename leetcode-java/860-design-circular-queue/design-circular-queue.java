class MyCircularQueue {
    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            next = null;
        }
    }

    private Node rear;
    private int size;
    private int capacity;

    public MyCircularQueue(int k) {
        this.rear = null;
        this.size = 0;
        this.capacity = k;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        Node node = new Node(value);
        if (isEmpty()) {
            node.next = node;
            rear = node;
        } else {
            node.next = rear.next; //new node -> rear.next which points to (front) because circular DS
            rear.next = node; //old rear points to new node
            rear = node; //move rear ptr fwd
        }
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }

        if (size == 1) {
            rear = null;
        } else {
            rear.next = rear.next.next;
        }
        size--;
        return true;
    }

    public int Front() {
        return isEmpty() ? -1 : rear.next.val;
    }

    public int Rear() {
        return isEmpty() ? -1 : rear.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */