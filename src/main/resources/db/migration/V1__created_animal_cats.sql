CREATE SCHEMA IF NOT EXISTS animal;

CREATE TABLE IF NOT EXISTS animal.cats (
id bigserial NOT NULL PRIMARY KEY,
cat_id_integration CHARACTER VARYING(36) NOT NULL,
breed CHARACTER VARYING(600) NOT NULL,
origin CHARACTER VARYING(40),
temperament TEXT,
description TEXT,
created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS animal.cat_photos (
id bigserial NOT NULL PRIMARY KEY,
cat_id bigint NOT NULL,
photo_id bigint NOT NULL,
created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS animal.photo_animals (
id bigserial NOT NULL PRIMARY KEY,
id_photo CHARACTER VARYING(36) NOT NULL,
width INTEGER,
height INTEGER,
url CHARACTER VARYING(100) NOT NULL,
created_at TIMESTAMP
);

ALTER TABLE animal.cat_photos
    ADD CONSTRAINT fk_cats FOREIGN KEY (cat_id) REFERENCES animal.cats (id);

ALTER TABLE animal.cat_photos
    ADD CONSTRAINT fk_photo_animals FOREIGN KEY (photo_id) REFERENCES animal.photo_animals (id);