

create table public.categorys
(
    id          integer generated always as identity
        primary key,
    name        varchar(255) not null,
    description varchar(255) not null
);

alter table public.categorys
    owner to postgres;

