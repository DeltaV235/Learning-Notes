-- create
CREATE TABLE course (
                        id INTEGER PRIMARY KEY,
                        name TEXT NOT NULL
);

CREATE TABLE student (
                         id INTEGER PRIMARY KEY,
                         name TEXT NOT NULL
);

CREATE TABLE score (
                       id INTEGER PRIMARY KEY,
                       student_id INTEGER NOT NULL,
                       course_id INTEGER NOT NULL,
                       score INTEGER NOT NULL
);

-- insert
INSERT INTO student VALUES (1, 's1');
INSERT INTO student VALUES (2, 's2');
INSERT INTO student VALUES (3, 's3');

INSERT INTO course VALUES (1, '语文');
INSERT INTO course VALUES (2, '数学');
INSERT INTO course VALUES (3, '外语');

INSERT INTO score VALUES (1, 1, 1, 100);
INSERT INTO score VALUES (2, 2, 1, 100);
INSERT INTO score VALUES (3, 3, 1, 100);
INSERT INTO score VALUES (4, 1, 2, 100);
INSERT INTO score VALUES (5, 2, 2, 100);
INSERT INTO score VALUES (6, 2, 3, 100);
INSERT INTO score VALUES (7, 3, 3, 100);