create table public.users
(
    id        integer generated always as identity
        primary key,
    username  varchar(255)                                   not null,
    password  varchar(255)                                   not null,
    full_name varchar(255)                                   not null,
    email     varchar(255)                                   not null,
    phone     varchar(255)                                   not null,
    address   varchar(255)                                   not null,
    role      varchar(255) default 'USER'::character varying not null
);

alter table public.users
    owner to postgres;

