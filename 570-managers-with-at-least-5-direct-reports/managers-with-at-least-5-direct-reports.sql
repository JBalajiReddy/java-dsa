# Write your MySQL query statement below
SELECT name FROM Employee WHERE id IN
(
   SELECT managerID FROM EMPLOYEE GROUP BY managerId HAVING COUNT(id) >= 5
);