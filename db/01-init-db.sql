DROP TABLE IF EXISTS worker CASCADE;
DROP TABLE IF EXISTS department CASCADE;

CREATE TABLE worker
(
    worker_id  SERIAL       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    age        INTEGER      NOT NULL,
    department VARCHAR(255) NOT NULL,
    CONSTRAINT worker_pk PRIMARY KEY (worker_id)
);

CREATE TABLE department
(
    id         BIGINT       NOT NULL,
    short_name VARCHAR(255) NOT NULL,
    ceo        VARCHAR(255) NOT NULL,
    address    VARCHAR(255) NOT NULL,
    CONSTRAINT pk_department PRIMARY KEY (id)
);