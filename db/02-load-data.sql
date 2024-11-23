-- liquibase formatted sql

-- changeset hals:2024-04-25-worker_insert_info
-- comment: предварительное наполнение табл Работник данными

INSERT INTO worker.worker(worker_id, name, age, department_id, address, salary, created_at, updated_at)
VALUES (nextval('worker_seq'), 'John Smith', 37, 4, '55555000, Raccoon city, USA', 17532.0, now(), now()),
       (nextval('worker_seq'), 'Shia LaBeouf', 28, 4, '55555000, Raccoon city, USA', 18532.10, now(), now()),
       (nextval('worker_seq'), 'Olli Wood', 33, 5, '55555000, Raccoon city, USA', 18532.10, now(), now());

INSERT INTO department(id, short_name, ceo, address)
VALUES (nextval('worker_seq'), 'Umbrella Corp', 'Alice Marcus', 'Raccoon city, USA'),
       (nextval('worker_seq'), 'Uber Corp', 'Marcus Bruce', 'Raccoon city, USA');

COMMENT ON COLUMN worker.worker_id IS 'Идентификатор Работника';
COMMENT ON COLUMN worker.name IS 'Имя Работника';
COMMENT ON COLUMN worker.age IS 'Возраст Работника';
COMMENT ON COLUMN worker.department_id IS 'Идентификатор Отдела';
COMMENT ON COLUMN worker.address IS 'Адрес Работника';
COMMENT ON COLUMN worker.salary IS 'Зарплата Работника';
COMMENT ON COLUMN worker.created_at IS 'Дата создания записи';
COMMENT ON COLUMN worker.updated_at IS 'Дата обновления записи';

COMMENT ON COLUMNT department.id IS 'Идентификатор Отдела';
COMMENT ON COLUMN department.short_name IS 'Название Отдела';
COMMENT ON COLUMN department.ceo IS 'Генеральный Директор Отдела';
COMMENT ON COLUMN department.address IS 'Адрес Отдела';