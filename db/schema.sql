DROP TABLE IF EXISTS develop.worker;

CREATE TABLE develop.worker
(
    worker_id  SERIAL       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    age        INTEGER      NOT NULL,
    department VARCHAR(255) NOT NULL,
    CONSTRAINT worker_pk PRIMARY KEY (worker_id)
);