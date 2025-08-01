class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int sum = 1, i = 1;

        while (i < n) {
            //flat
            if (ratings[i] == ratings[i - 1]) {
                sum++;
                i++;
                continue;
            }

            //peak - We are assigning candies immediately while walking the slope (since next child must get more).
            int peak = 1;
            while (i < n && ratings[i] > ratings[i - 1]) {
                peak++;
                sum += peak;
                i++;
            }

            //down - We are just counting how long it is, and compute candies in bulk after the downhill ends.
            int down = 0;
            while (i < n && ratings[i] < ratings[i - 1]) {
                down++;
                sum += down;
                i++;
            }
            down++;
            if (down > peak)
                sum += (down - peak);
        }
        return sum;
    }
}