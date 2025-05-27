class Solution {

    public int differenceOfSums(int n, int m) {
        int k = n / m;
        return (n * (n + 1)) / 2 - k * (k + 1) * m;
    }
}



// class Solution {
//     public int differenceOfSums(int n, int m) {
//         int num1 = 0, num2 = 0;
//         for (int i = 1; i < n + 1; i++) {
//             if (i % m != 0) {
//                 num1 += i;
//             }
//            else if (i % m == 0) {
//                 num2 += i;
//             }
//         }
//         return num1 - num2;
//     }
// }