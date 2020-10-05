DROP TABLE IF EXISTS public.tags;

CREATE TABLE public.tags (
    id integer NOT NULL,
    title character varying(255) NOT NULL,
    image bytea
);