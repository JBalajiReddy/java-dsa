class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        //nearest point
        int xi;
        int yi;

        if (x1 > xCenter) {
            xi = x1;
        } else if (x2 < xCenter) {
            xi = x2;
        } else {
            xi = xCenter;
        }

        if (y1 > yCenter) {
            yi = y1;
        } else if (y2 < yCenter) {
            yi = y2;
        } else {
            yi = yCenter;
        }

        //(xi, yi) ------- (xCenter, yCenter)
        long dx = xi - xCenter;
        long dy = yi - yCenter;
        return dx * dx + dy * dy <= (long) radius * radius;
    }
}

class Solution1 {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Math.clamp(value, min, max) restricts value within [min, max].
        // If xCenter < x1, it returns x1; if xCenter > x2, it returns x2; otherwise, it returns xCenter.
        // This instantly finds the nearest point (xi, yi) on/inside the rectangle to the circle center.
        int xi = Math.clamp(xCenter, x1, x2);
        int yi = Math.clamp(yCenter, y1, y2);

        // Calculate distance components from nearest point (xi, yi) to circle center (xCenter, yCenter)
        long dx = xi - xCenter;
        long dy = yi - yCenter;

        // Check if squared distance is within squared radius (avoids Math.sqrt precision issues)
        return dx * dx + dy * dy <= (long) radius * radius;
    }
}


class Solution2 {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Step 1: Project (clamp) the circle's center onto the nearest point 
        // inside or on the border of the axis-aligned rectangle.

        // Clamp xCenter to stay within the horizontal bounds [x1, x2]
        int nearestX = Math.max(x1, Math.min(xCenter, x2));

        // Clamp yCenter to stay within the vertical bounds [y1, y2]
        int nearestY = Math.max(y1, Math.min(yCenter, y2));

        // Step 2: Calculate the distance components (dx, dy) between 
        // the circle's center and the closest point on the rectangle.
        int deltaX = xCenter - nearestX;
        int deltaY = yCenter - nearestY;

        // Step 3: Check if the squared distance is <= squared radius.
        // Using squared values (dx^2 + dy^2 <= r^2) avoids using Math.sqrt(),
        // which prevents floating-point precision issues and runs faster.
        return (deltaX * deltaX + deltaY * deltaY) <= radius * radius;
    }
}