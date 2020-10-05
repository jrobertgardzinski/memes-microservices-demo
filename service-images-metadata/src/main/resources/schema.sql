DROP TABLE IF EXISTS public.images_metadata;

CREATE TABLE public.images_metadata (
    id integer NOT NULL,
    title character varying(255) NOT NULL,
    image bytea
);