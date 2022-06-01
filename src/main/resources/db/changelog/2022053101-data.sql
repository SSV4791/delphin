--liquibase formatted sql

--changeset SSV:2022053101

insert into person (name) values ('person_1');

insert into task (person_id, name) values ((select id from person where name = 'person_1'), 'task_1_for_person_1');
insert into task (person_id, name) values ((select id from person where name = 'person_1'), 'task_2_for_person_1');

--rollback delete from task where person_id = (select id from person where name = 'person_1') and (name = 'task_1_for_person_1' or name = 'task_2_for_person_1')
--rollback delete from person where name = 'person_1'