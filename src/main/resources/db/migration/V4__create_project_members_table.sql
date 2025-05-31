-- V4__create_project_members_table.sql

CREATE table project_members
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    project_id   BIGINT       NOT NULL,
    user_id      BIGINT       NOT NULL,
    project_role VARCHAR(20) NOT NULL,
    CONSTRAINT fk_project_member_project FOREIGN KEY (project_id) REFERENCES projects (id),
    CONSTRAINT fk_project_member_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT uc_project_user UNIQUE (project_id, user_id)
)