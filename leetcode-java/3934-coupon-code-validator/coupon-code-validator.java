class Solution {
    private static class Coupon {
        String code;
        String category;

        Coupon(String code, String category) {
            this.code = code;
            this.category = category;
        }
    }

    public List<String> validateCoupons(String[] codes, String[] businessLine, boolean[] isActive) {
        Map<String, Integer> priority = new HashMap<>();
        priority.put("electronics", 0);
        priority.put("grocery", 1);
        priority.put("pharmacy", 2);
        priority.put("restaurant", 3);

        List<Coupon> validCoupons = new ArrayList<>();

        for (int i = 0; i < codes.length; i++) {
            if (isActive[i] && priority.containsKey(businessLine[i]) && isValidCode(codes[i])) {
                validCoupons.add(new Coupon(codes[i], businessLine[i]));
            }
        }
        validCoupons.sort((a, b) -> {
            int priorityA = priority.get(a.category);
            int priorityB = priority.get(b.category);

            // Primary Sort: Business Line
            if (priorityA != priorityB) {
                return Integer.compare(priorityA, priorityB);
            }
            // Secondary Sort: Code (Lexicographical)
            return a.code.compareTo(b.code);
        });

        List<String> res = new ArrayList<>();
        for (Coupon c : validCoupons) {
            res.add(c.code);
        }

        return res;
    }

    private boolean isValidCode(String code) {
        if (code == null || code.isEmpty())
            return false;
        for (char ch : code.toCharArray()) {
            boolean isLower = (ch >= 'a' && ch <= 'z');
            boolean isUpper = (ch >= 'A' && ch <= 'Z');
            boolean isDigit = (ch >= '0' && ch <= '9');
            boolean isUnderscore = (ch == '_');

            if (!isLower && !isUpper && !isDigit && !isUnderscore) {
                return false;
            }
        }
        return true;
    }
}