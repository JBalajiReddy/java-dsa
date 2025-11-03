# Write your MySQL query statement below

SELECT
  ROW_NUMBER() OVER(ORDER BY IF(MOD(id, 2) = 0, id - 1, id + 1)) AS id,
  student
FROM Seat;

-- SELECT 
--     CASE
--         WHEN id = (SELECT MAX(id) FROM Seat) AND MOD(id, 2) = 1
--             THEN id
--         WHEN MOD(id, 2) = 1
--             THEN id + 1
--         ELSE id - 1
--     END AS id, student
-- FROM Seat
-- ORDER BY id;