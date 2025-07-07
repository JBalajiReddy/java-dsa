# Write your MySQL query statement below


-- %Y - full year, %y - 2 digits of year
SELECT DATE_FORMAT(trans_date, '%Y-%m') AS month, country,
COUNT(id) AS trans_count,
SUM(state = 'approved') AS approved_count,
-- COUNT(CASE WHEN state = 'approved' THEN 1 END)
SUM(amount) AS trans_total_amount,
SUM(IF(state = 'approved', amount, 0))  AS approved_total_amount
FROM Transactions
GROUP BY month, country;