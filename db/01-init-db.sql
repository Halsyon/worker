-- liquibase formatted sql

-- changeset hals:2024-04-25-worker_insert_info
-- comment: предварительное создание табл БД Работник
DROP TABLE IF EXISTS worker CASCADE;
DROP TABLE IF EXISTS department CASCADE;

DROP SEQUENCE IF EXISTS worker.worker_seq;
DROP SCHEMA IF EXISTS worker;

CREATE SCHEMA worker;
CREATE SEQUENCE worker.worker_seq START WITH 10 INCREMENT BY 1;

CREATE TABLE worker.worker
(
    worker_id     BIGINT           NOT NULL,
    name          VARCHAR(255)     NOT NULL,
    age           INTEGER          NOT NULL,
    department_id BIGINT           NOT NULL,
    address       VARCHAR(255)     NOT NULL,
    salary        DOUBLE PRECISION NOT NULL,
    created_at    BIGINT           NOT NULL,
    updated_at    BIGINT           NOT NULL,
    CONSTRAINT pk_worker PRIMARY KEY (worker_id)
);

CREATE INDEX idx_worker_name ON worker.worker (name);

ALTER TABLE worker.worker
    ADD CONSTRAINT FK_WORKER_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES worker.department (id);;

CREATE TABLE worker.department
(
    id         BIGINT       NOT NULL,
    short_name VARCHAR(255) NOT NULL,
    ceo        VARCHAR(255) NOT NULL,
    address    VARCHAR(255) NOT NULL,
    CONSTRAINT pk_department PRIMARY KEY (id)
);