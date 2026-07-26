/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
*/

class Solution {
    public Node construct(int[][] grid) {
        return dfs(grid, grid.length, 0, 0);
    }

    private Node dfs(int[][] grid, int n, int r, int c) {
        // Base case: A 1x1 grid is always a leaf
        if (n == 1) {
            return new Node(grid[r][c] == 1, true);
        }

        int mid = n / 2;

        // Divide into 4 quadrants using (r, c) offsets
        Node topLeft = dfs(grid, mid, r, c);
        Node topRight = dfs(grid, mid, r, c + mid);
        Node bottomLeft = dfs(grid, mid, r + mid, c);
        Node bottomRight = dfs(grid, mid, r + mid, c + mid);

        // Conquer: Check if all 4 quadrants can be merged into 1 leaf
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && topLeft.val == topRight.val
                && topRight.val == bottomLeft.val
                && bottomLeft.val == bottomRight.val) {
            return new Node(topLeft.val, true); // Compact 4 leaves into 1 parent leaf
        }

        // Otherwise, construct an internal node linking the 4 children
        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }
}