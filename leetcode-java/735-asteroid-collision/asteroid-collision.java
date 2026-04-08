class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for (int num : asteroids) {
            if (num > 0) {
                st.push(num);
            } else {
                int pNum = -num;
                while (!st.isEmpty() && st.peek() > 0 && st.peek() < pNum) {
                    st.pop();
                }
                if (st.isEmpty() || st.peek() < 0) {
                    st.push(num);
                }
                if (st.peek() == pNum) {
                    st.pop();
                }
            }

        }
        return st.stream().mapToInt(i -> i).toArray();
    }
}