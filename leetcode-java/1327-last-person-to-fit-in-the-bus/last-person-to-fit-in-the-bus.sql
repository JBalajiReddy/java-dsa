# Write your MySQL query statement below

WITH CumulativeWeight AS (
  SELECT
    person_name,
    turn,
    weight,
    SUM(weight) OVER (ORDER BY turn) AS running_total
  FROM Queue
)
SELECT person_name
FROM CumulativeWeight
WHERE running_total <= 1000
ORDER BY running_total DESC
LIMIT 1;

-- SELECT q1.person_name FROM Queue q1
-- INNER JOIN Queue q2
-- ON q1.turn >= q2.turn
-- GROUP BY q1.turn
-- HAVING SUM(q2.weight) <= 1000
-- ORDER BY SUM(q2.weight) DESC
-- LIMIT 1;