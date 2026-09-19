class Solution {
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