class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();

        for (int num : asteroids) {
            // Positive asteroids move right; they cannot collide with anything currently in the stack
            if (num > 0) {
                st.push(num);
            } else {
                // Negative asteroid moves left; convert to positive magnitude for comparison
                int pNum = -num;

                // Collision loop: pop smaller right-moving (> 0) asteroids that get destroyed by pNum
                while (!st.isEmpty() && st.peek() > 0 && st.peek() < pNum) {
                    st.pop();
                }

                // If stack is empty or top asteroid is moving left (< 0), no collision occurs; push pNum
                if (st.isEmpty() || st.peek() < 0) {
                    st.push(num);
                } 
                // Equal size collision: both asteroids destroy each other, so pop the right-moving one
                else if (st.peek() == pNum) {
                    st.pop();
                }
                // (Implicit else: if st.peek() > pNum, the left-moving asteroid is destroyed; do nothing)
            }
        }

        // Convert the remaining stack back into an array
        return st.stream().mapToInt(i -> i).toArray();
    }
}