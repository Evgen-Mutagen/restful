DELETE FROM tasks;
DELETE FROM reports;
ALTER SEQUENCE global_seq_rep RESTART WITH 10000;
ALTER SEQUENCE global_seq_task RESTART WITH 100000;

INSERT INTO reports (time_of_report)
VALUES ('2022-10-02 10:00:00'),
       ('2022-10-01 12:00:00'),
       ('2022-09-29 14:00:00');


INSERT INTO tasks (time_of_task, user_name,task, report_id)
VALUES ('2022-10-01 12:00:00', 'user1', 'task1', 10000),
       ('2022-10-01 12:00:00', 'user2', 'task2', 10001),
       ('2022-10-01 12:00:00', 'user3', 'task3', 10002);
