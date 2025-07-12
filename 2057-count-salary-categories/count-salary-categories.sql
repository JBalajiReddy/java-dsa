# Write your MySQL query statement below
WITH l_salary AS (
    SELECT * FROM Accounts WHERE income < 20000
),

avg_salary AS (
    SELECT * FROM Accounts WHERE income BETWEEN 20000 AND 50000
),

h_salary AS (
    SELECT * FROM Accounts WHERE income > 50000
)

SELECT 'Low Salary' AS category, COUNT(*) AS accounts_count FROM l_salary
UNION
SELECT 'Average Salary' AS category, COUNT(*) AS accounts_count FROM avg_salary
UNION
SELECT 'High Salary' AS category, COUNT(*) AS accounts_count FROM h_salary;



-- SELECT "Low Salary" as category, sum(if(income<20000,1,0)) AS accounts_count FROM Accounts
-- union
-- SELECT "Average Salary" as category, sum(if(income>=20000 and income<=50000,1,0)) AS accounts_count FROM Accounts
-- union
-- SELECT "High Salary" as category, sum(if(income>50000,1,0)) AS accounts_count FROM Accounts