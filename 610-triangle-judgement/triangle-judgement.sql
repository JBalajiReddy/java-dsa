# Write your MySQL query statement below

-- IF --
SELECT x, y, z, IF(x + y > z AND y + z > x AND x + z > y, "Yes", "No") AS triangle
FROM triangle;