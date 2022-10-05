DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS reports;
DROP SEQUENCE IF EXISTS global_seq_rep;
DROP SEQUENCE IF EXISTS global_seq_task;

CREATE SEQUENCE global_seq_rep START WITH 10000;

CREATE TABLE reports (
    id_report             INTEGER PRIMARY KEY DEFAULT nextval('global_seq_rep'),
    time_of_report TIMESTAMP                   NOT NULL
);

CREATE SEQUENCE global_seq_task START WITH 100000;

CREATE TABLE tasks (
    id           INTEGER PRIMARY KEY DEFAULT nextval('global_seq_task'),
    time_of_task TIMESTAMP                   NOT NULL,
    user_name    VARCHAR                     NOT NULL UNIQUE,
    task         VARCHAR                     NOT NULL UNIQUE,
    report_id    INTEGER                     NOT NULL DEFAULT nextval('global_seq_rep'),
    FOREIGN KEY (report_id) REFERENCES reports (id_report) ON DELETE CASCADE
);
