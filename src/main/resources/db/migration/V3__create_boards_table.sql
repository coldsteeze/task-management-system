-- V3__create_board_table.sql

CREATE TABLE boards (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(100) NOT NULL,
                        project_id BIGINT NOT NULL,
                        CONSTRAINT fk_board_project FOREIGN KEY (project_id) REFERENCES projects(id),
                        CONSTRAINT uc_board_name_project UNIQUE (project_id, name)
);