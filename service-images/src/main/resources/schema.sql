DROP TABLE IF EXISTS public.images;

CREATE TABLE public.images (
    id integer NOT NULL,
    title character varying(255) NOT NULL,
    image bytea
);