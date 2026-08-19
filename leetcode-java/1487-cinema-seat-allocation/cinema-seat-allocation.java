class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        /* 
         * BITMASK LAYOUT:
         * We only care about seats 2 through 9 (8 seats total).
         * We map seat numbers to an 8-bit integer (bits 0 to 7):
         *   Seat 2 -> bit 0 (1 << 0)
         *   Seat 3 -> bit 1 (1 << 1)
         *   ...
         *   Seat 9 -> bit 7 (1 << 7)
         * 
         * A bit value of '1' in a row's bitmask means that seat is RESERVED.
         * A bit value of '0' means that seat is FREE.
         */

        /*
         * TARGET FAMILY BLOCKS & THEIR MASKS:
         * 
         * 1. LEFT BLOCK needs seats 2, 3, 4, 5 (bits 0, 1, 2, 3) to be FREE (0).
         *    Mask `left` = 0b11110000 (bits 4-7 set to 1).
         *    - If a row's bitmask has reserved seats ONLY in bits 4-7 (seats 6-9),
         *      ORing it with `left` leaves `left` unchanged (0b11110000).
         *    - If seats 2-5 have any reservation, ORing will set extra bits in 0-3,
         *      making (bitmask | left) != left.
         */
        int left = 0b11110000;

        /*
         * 2. MIDDLE BLOCK needs seats 4, 5, 6, 7 (bits 2, 3, 4, 5) to be FREE (0).
         *    Mask `middle` = 0b11000011 (bits 0, 1, 6, 7 set to 1).
         *    - Bits 2-5 MUST be 0 for (bitmask | middle) == middle to hold true.
         */
        int middle = 0b11000011;

        /*
         * 3. RIGHT BLOCK needs seats 6, 7, 8, 9 (bits 4, 5, 6, 7) to be FREE (0).
         *    Mask `right` = 0b00001111 (bits 0-3 set to 1).
         *    - Bits 4-7 MUST be 0 for (bitmask | right) == right to hold true.
         */
        int right = 0b00001111;

        // Map key: row number -> Map value: 8-bit integer tracking reserved seats
        Map<Integer, Integer> occupied = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Seats 1 and 10 can never block a 4-person block (seats 2-5, 4-7, 6-9),
            // so we ignore them completely.
            if (col >= 2 && col <= 9) {
                int currentMask = occupied.getOrDefault(row, 0);

                // (col - 2) shifts seat index so seat 2 becomes bit 0, seat 9 becomes bit 7.
                // Bitwise OR '|=' sets that specific bit to 1 (marking the seat reserved).
                occupied.put(row, currentMask | (1 << (col - 2)));
            }
        }

        // Empty rows can always host 2 families (Left block + Right block).
        // Calculate families for all rows that have NO reservations at all.
        int maxFamilies = (n - occupied.size()) * 2;

        // Process only the rows that have at least one reservation in seats 2-9
        for (int bitmask : occupied.values()) {
            /*
             * KEY GOTCHA / TRICK:
             * Since this row already has >= 1 reserved seat in seats 2-9, 
             * it is IMPOSSIBLE to place 2 families here (placing 2 families requires 
             * ALL seats 2-9 to be free). So a reserved row can yield AT MOST 1 family.
             * 
             * We check if AT LEAST ONE block (Left, Middle, or Right) is valid using:
             *   (bitmask | mask) == mask
             * 
             * Why this formula works:
             * - `mask` has 1s in all positions EXCEPT the required free seats.
             * - If `bitmask` has 0s in those required positions, `(bitmask | mask)`
             *   will not add any new 1s, leaving the result equal to `mask`.
             * - If `bitmask` has a 1 in a required position, `(bitmask | mask)` 
             *   will set a bit where `mask` had a 0, changing its value!
             */
            if ((bitmask | left) == left ||
                    (bitmask | middle) == middle ||
                    (bitmask | right) == right) {
                maxFamilies++;
            }
        }

        return maxFamilies;
    }
}

class Solution_BitMasking {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> mp = new HashMap<>(); //row -> bitmask of booked seats

        for (int[] reservedSeat : reservedSeats) { //O(10^4)
            int row = reservedSeat[0];
            int seat = reservedSeat[1];
            mp.merge(row, (1 << seat), (a, b) -> a | b); //set bits are the booked seats

            // Traditional Approach:
            //int existingMask = mp.getOrDefault(row, 0);
            //mp.put(row, existingMask | (1 << seat));

            // Concise Approach using merge():
            // mp.merge(row, (1 << seat), (a, b) -> a | b);
        }

        int result = (n - mp.size()) * 2;

        int maskA = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5); //set bits are the ones I need empty for Group A
        int maskB = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7); //set bits are the ones I need empty for Group B
        int maskC = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9); //set bits are the ones I need empty for Group C

        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) { //min(10*n, 10^4)
            int bookedSeatsMask = entry.getValue();

            boolean groupA = (bookedSeatsMask & maskA) == 0;
            boolean groupB = (bookedSeatsMask & maskB) == 0;
            boolean groupC = (bookedSeatsMask & maskC) == 0;

            if (groupA && groupC)
                result += 2;
            else if (groupA || groupB || groupC)
                result += 1;
        }

        return result;
    }
}

class Solution_HashMap {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> mp = new HashMap<>(); //row -> seats booked in each row

        for (int[] reservedSeat : reservedSeats) {
            int row = reservedSeat[0];
            int seat = reservedSeat[1];
            mp.computeIfAbsent(row, k -> new HashSet<>()).add(seat);
        }

        int result = (n - mp.size()) * 2;

        for (Map.Entry<Integer, Set<Integer>> entry : mp.entrySet()) {
            Set<Integer> bookedSeats = entry.getValue();

            boolean groupA = !bookedSeats.contains(2) && !bookedSeats.contains(3) && !bookedSeats.contains(4)
                    && !bookedSeats.contains(5);
            boolean groupB = !bookedSeats.contains(4) && !bookedSeats.contains(5) && !bookedSeats.contains(6)
                    && !bookedSeats.contains(7);
            boolean groupC = !bookedSeats.contains(6) && !bookedSeats.contains(7) && !bookedSeats.contains(8)
                    && !bookedSeats.contains(9);

            if (groupA && groupC)
                result += 2;
            else if (groupA || groupB || groupC)
                result += 1;
        }

        return result;
    }
}
