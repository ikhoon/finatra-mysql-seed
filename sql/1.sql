-- UP
CREATE DATABASE IF NOT EXISTS finatra_test;

CREATE TABLE IF NOT EXISTS finatra_test.USERS (
  id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name VARCHAR(128),
  created_at DATETIME
);

TRUNCATE finatra_test.USERS;

INSERT INTO finatra_test.USERS (name, created_at) VALUES ('kim', NOW());
INSERT INTO finatra_test.USERS (name, created_at) VALUES ('park', NOW());
INSERT INTO finatra_test.USERS (name, created_at) VALUES ('lee', NOW());
