-- 求所有同学都有考试记录的课程有哪些
-- Query to find courses where all students have exam records

SELECT c.name AS course_name
FROM course c
         JOIN score s ON c.id = s.course_id
GROUP BY c.name
HAVING COUNT(DISTINCT s.student_id) = (SELECT COUNT(*) FROM student);

SELECT c.id, c.name
FROM course c
WHERE NOT EXISTS (
-- 子查询：查找没有参加当前课程考试的学生
    SELECT 1
    FROM student s
    WHERE NOT EXISTS (
-- 子查询：查找当前学生是否有当前课程的考试记录
        SELECT 1
        FROM score sc
        WHERE sc.student_id = s.id
          AND sc.course_id = c.id));