-- V1__create_users_table.sql

CREATE TABLE users
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(20)                         NOT NULL UNIQUE,
    email      VARCHAR(255)                        NOT NULL UNIQUE,
    password   VARCHAR(255)                        NOT NULL,
    role       VARCHAR(255)                        NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);