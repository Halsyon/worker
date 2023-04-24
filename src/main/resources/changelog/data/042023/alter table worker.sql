-- liquibase formatted sql

-- changeset hals:2023-04-25-worker_insert_info
-- comment: предварительное наполнение табл Работник данными
ALTER TABLE research."worker".worker ADD COLUMN "period" VARCHAR(255);