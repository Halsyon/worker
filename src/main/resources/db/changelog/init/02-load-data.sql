-- liquibase formatted sql

-- changeset hals:2023-04-22-worker_insert_info
-- comment: ���������� - ������� worker � �������� ����� ����������

INSERT INTO worker."worker" (worker_id, name, age, department)
VALUES (1, 'John Smith', 37, 'IT'),
       (2, 'Shia LaBeouf', 28, 'IT'),
       (3, 'Olli Wood', 33, 'IT');