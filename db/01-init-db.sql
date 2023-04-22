DROP TABLE IF EXISTS worker CASCADE;

CREATE TABLE worker
(
    worker_id  SERIAL       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    age        INTEGER      NOT NULL,
    department VARCHAR(255) NOT NULL,
    CONSTRAINT worker_pk PRIMARY KEY (worker_id)
);