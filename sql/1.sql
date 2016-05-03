-- UP
CREATE DATABASE IF NOT EXISTS finatra_test;

DROP TABLE IF EXISTS finatra_test.USERS;
DROP TABLE IF EXISTS finatra_test.POINTS;

CREATE TABLE IF NOT EXISTS finatra_test.USERS (
  id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name VARCHAR(128),
  email VARCHAR(128),
  created_at DATETIME
);

CREATE TABLE IF NOT EXISTS finatra_test.POINTS (
  id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  user_id BIGINT,
  point BIGINT,
  created_at DATETIME
);

TRUNCATE finatra_test.USERS;
TRUNCATE finatra_test.POINTS;

INSERT INTO finatra_test.USERS (id, name, email, created_at) VALUES (1, 'Kim', 'k@k.net', NOW());
INSERT INTO finatra_test.USERS (id, name, email, created_at) VALUES (2, 'Park','p@p.net', NOW());
INSERT INTO finatra_test.USERS (id, name, email, created_at) VALUES (3, 'Lee', 'l@l.net',NOW());
INSERT INTO finatra_test.USERS (id, name, email, created_at) VALUES (4, 'Choi', 'c@c.net',NOW());

INSERT INTO finatra_test.POINTS (id, user_id, point, created_at) VALUES (1, 1, 1000, NOW());
INSERT INTO finatra_test.POINTS (id, user_id, point, created_at) VALUES (2, 2, 2000, NOW());
INSERT INTO finatra_test.POINTS (id, user_id, point, created_at) VALUES (3, 3, 3000, NOW());
INSERT INTO finatra_test.POINTS (id, user_id, point, created_at) VALUES (4, 4, 4000, NOW());
