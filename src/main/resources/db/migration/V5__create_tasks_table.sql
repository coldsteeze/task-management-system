-- V5__create_tasks_table.sql

CREATE TABLE tasks
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(100)                        NOT NULL,
    description VARCHAR(255),
    status      VARCHAR(20)                         NOT NULL,
    assignee_id BIGINT,
    board_id    BIGINT                              NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_task_user FOREIGN KEY (assignee_id) REFERENCES users (id),
    CONSTRAINT fk_task_board FOREIGN KEY (board_id) REFERENCES boards (id)
);