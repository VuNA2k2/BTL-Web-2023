CREATE TABLE IF NOT EXISTS public.products
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY
(
    INCREMENT 1 START 1
),
    name varchar NOT NULL,
    description varchar,
    price integer NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY
(
    id
)
    );
ALTER TABLE public.products
    ADD COLUMN category_id integer NOT NULL;

ALTER TABLE public.products
    ADD CONSTRAINT products_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.categorys(id);