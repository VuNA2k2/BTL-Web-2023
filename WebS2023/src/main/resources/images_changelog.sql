CREATE TABLE images
(
    id      INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START 1 INCREMENT 1),
    link    VARCHAR(255) NOT NULL,
    refId   INTEGER      NOT NULL,
    refType VARCHAR(255) NOT NULL
)

alter table images
    rename column refid to ref_id;

alter table images
    rename column reftype to ref_type;
