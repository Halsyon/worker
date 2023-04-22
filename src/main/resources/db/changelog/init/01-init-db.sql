-- liquibase formatted sql

-- changeset hals:2023-04-22-worker
-- comment: таблица worker к договору найма сотрудника
CREATE SEQUENCE IF NOT EXISTS worker.hibernate_sequence AS bigint START WITH 1000 INCREMENT BY 1 MINVALUE 1 NO MAXVALUE CACHE 1;
CREATE SEQUENCE IF NOT EXISTS worker.documents_sequence START WITH 1000 INCREMENT BY 1 MINVALUE 1 NO MAXVALUE CACHE 1;
CREATE SEQUENCE IF NOT EXISTS worker.log_sequence AS bigint START WITH 1000 INCREMENT BY 1 MINVALUE 1 NO MAXVALUE CACHE 1;

DROP TABLE IF EXISTS worker.worker CASCADE;

CREATE TABLE worker.worker
(
    worker_id  BIGINT       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    age        INTEGER      NOT NULL,
    department VARCHAR(255) NOT NULL,
    CONSTRAINT worker_pk PRIMARY KEY (worker_id)
);

COMMENT ON TABLE worker."worker" IS 'Таблица к договору найма сотрудника';
COMMENT ON TABLE worker."worker" IS 'ФИО сотрудника';
COMMENT ON TABLE worker."worker" IS 'Возраст сотрудника';
COMMENT ON TABLE worker."worker" IS 'Подразделение к которому прикреплен сотрудник';
