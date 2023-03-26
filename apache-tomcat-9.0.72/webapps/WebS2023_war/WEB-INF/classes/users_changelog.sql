-- first create table users
CREATE TABLE IF NOT EXISTS public.users(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1),
    CONSTRAINT users_pkey PRIMARY KEY (id),
    name varchar NOT NULL,
    date_of_birth varchar NOT NULL
)
