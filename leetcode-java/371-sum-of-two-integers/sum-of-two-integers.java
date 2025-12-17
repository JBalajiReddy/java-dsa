class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            int tmp = a ^ b;
            int carry = (a & b) << 1;
            a = tmp;
            b = carry;
        }
        return a;
    }
}

// class Solution {
//     public int getSum(int a, int b) {
//         int carry = 0;
//         while (b != 0) {
//             carry = a & b;
//             a = a ^ b;
//             b = carry << 1;
//         }
//         return a;
//     }
// }