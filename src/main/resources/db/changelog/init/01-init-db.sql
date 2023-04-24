-- liquibase formatted sql

-- changeset hals:2023-04-22-worker
-- comment: таблица worker Работник закрепленный за определенным департаментом
CREATE SEQUENCE IF NOT EXISTS research.worker.hibernate_sequence AS bigint START WITH 1000 INCREMENT BY 1 MINVALUE 1 NO MAXVALUE CACHE 1;
CREATE SEQUENCE IF NOT EXISTS research.worker.documents_sequence START WITH 1000 INCREMENT BY 1 MINVALUE 1 NO MAXVALUE CACHE 1;
CREATE SEQUENCE IF NOT EXISTS research.worker.log_sequence AS bigint START WITH 1000 INCREMENT BY 1 MINVALUE 1 NO MAXVALUE CACHE 1;

DROP TABLE IF EXISTS  research."worker".worker CASCADE;

CREATE TABLE research."worker".worker
(
    worker_id  BIGINT       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    age        INTEGER      NOT NULL,
    department VARCHAR(255) NOT NULL,
    CONSTRAINT worker_pk PRIMARY KEY (worker_id)
);

-- liquibase formatted sql

-- changeset hals:2023-04-22-worker_comment
-- comment: таблица worker - найминг
COMMENT ON TABLE worker."worker" IS 'Работник закрепленный за определенным департаментом';
COMMENT ON COLUMN worker."worker".age IS 'Возраст работника';
COMMENT ON COLUMN worker."worker".name IS 'Полное ФИО работника';
COMMENT ON COLUMN worker."worker".department IS 'Департамент к которому прикреплен работник';