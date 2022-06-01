--liquibase formatted sql

--changeset SSV:2022053001

create table person
(
    id         bigserial primary key       not null,
    name       character varying(128)      not null,
    created_at timestamp without time zone not null default now(),
    updated_on timestamp without time zone not null default now(),
    unique (name)
);

create table task
(
    id         bigserial primary key       not null,
    person_id  bigint not null,
    name       character varying(128)      not null,
    created_at timestamp without time zone not null default now(),
    updated_on timestamp without time zone not null default now(),
    foreign key(person_id) references person (id),
    unique (name, person_id)
);

--rollback drop table task
--rollback drop table person