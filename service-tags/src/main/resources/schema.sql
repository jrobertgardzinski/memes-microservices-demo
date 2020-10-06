DROP TABLE IF EXISTS public.tags;

CREATE TABLE public.tags (
    tag character varying(255) PRIMARY KEY NOT NULL,
    image_ids_array integer[] CHECK (image_ids_array <> '{}')
);