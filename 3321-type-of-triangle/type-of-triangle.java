class Solution {
    public String triangleType(int[] nums) {
        int a = nums[0], b = nums[1], c = nums[2];
        if (a + b <= c || a + c <= b || b + c <= a)
            return "none";
        if (a == b && b == c)
            return "equilateral";
        if (a == b || b == c || a == c)
            return "isosceles";
        return "scalene";
    }
}

// class Solution {
//     public String triangleType(int[] nums) {
//         Arrays.sort(nums);
//         int a = nums[0];
//         int b = nums[1];
//         int c = nums[2];
//         if (isTriangle(a, b, c)){
//             if (a == b && b == c) {
//                 return "equilateral";
//             } else if (a != b && b != c && c != a) {
//                 return "scalene";
//             } else {
//                 return "isosceles";
//             }
//         }
//         return "none";
//     }
//     private boolean isTriangle(int a, int b, int c) {
//         if (a + b <= c) {
//             return false;
//         }
//         return true;
//     }
// }