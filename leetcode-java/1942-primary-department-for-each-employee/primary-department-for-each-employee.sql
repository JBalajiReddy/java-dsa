# Write your MySQL query statement below
SELECT
employee_id, department_id
FROM Employee
WHERE primary_flag = 'Y' 
OR
employee_id IN (
    SELECT employee_id FROM Employee
    GROUP BY employee_id HAVING COUNT(department_id) = 1
)

-- Retrieving employees with primary_flag set to 'Y'
-- SELECT 
--   employee_id, 
--   department_id 
-- FROM 
--   Employee 
-- WHERE 
--   primary_flag = 'Y' 
-- UNION 
-- -- Retrieving employees that appear exactly once in the Employee table
-- SELECT 
--   employee_id, 
--   department_id 
-- FROM 
--   Employee 
-- GROUP BY 
--   employee_id 
-- HAVING 
--   COUNT(employee_id) = 1;