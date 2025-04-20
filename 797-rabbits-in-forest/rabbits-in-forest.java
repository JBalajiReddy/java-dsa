class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> answerCounts = new HashMap<>();

        for (int answer : answers) {

            answerCounts.put(answer, answerCounts.getOrDefault(answer, 0) + 1);
        }

        int res = 0;

        for (Map.Entry<Integer, Integer> entry : answerCounts.entrySet()) {
            int answer = entry.getKey(); // The number each rabbit answered
            int count = entry.getValue(); // How many rabbits gave this answer
            int groupSize = answer + 1; // Minimum group size for this color

            int numGroups = (int) Math.ceil((double) count / groupSize);

            res += numGroups * groupSize;
        }

        return res;
    }
}