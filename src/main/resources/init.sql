SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;
SET default_tablespace = '';
SET default_with_oids = false;
SET search_path TO public;

DROP TABLE IF EXISTS users cascade;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS orders_help CASCADE;
DROP TABLE IF EXISTS storage CASCADE;
DROP TABLE IF EXISTS wear CASCADE;
DROP TABLE IF EXISTS accessory CASCADE;
DROP TABLE IF EXISTS storage_accessory CASCADE;
DROP TABLE IF EXISTS storage_wear CASCADE;

drop type if exists element;
drop type if exists user_type;
CREATE TYPE element AS ENUM ('SKI', 'SKIBOOT', 'SKISTICK', 'SNOWBOARD', 'BOARDBOOT', 'JACKET', 'TROUSERS', 'HELMET', 'GLOVE', 'GOGGLESS');
CREATE TYPE user_type AS ENUM ('USER', 'ADMIN');

CREATE TABLE storage
(
    item_id serial      NOT NULL PRIMARY KEY,
    brand   VARCHAR(30) NOT NULL,
    NR      VARCHAR(30) NOT NULL,
    element element     NOT NULL,
    amount  integer,
    price   integer,
    pic     varchar(200)
);

create table wear
(
    item_id integer references storage(item_id),
    wear_id integer not null primary key,
    size varchar(5)
);

create table accessory
(
    item_id integer references storage(item_id),
    accessory_id integer not null primary key,
    length       integer,
    flex_index   integer
);

create table storage_wear
(
    storage_id integer references storage (item_id),
    wear_id    integer references wear (wear_id)
);
create table storage_accessory
(
    storage_id integer references storage (item_id),
    accessory  integer references accessory (accessory_id)
);

CREATE TABLE users
(
    ID_card_number VARCHAR(30) PRIMARY KEY NOT NULL,
    forename       VARCHAR(30)             NOT NULL,
    lastName       VARCHAR(30)             NOT NULL,
    email          VARCHAR(30)             NOT NULL,
    password       VARCHAR(30)             NOT NULL,
    country        varchar(30)             not null,
    city           VARCHAR(20)             NOT NULL,
    zip_code       varchar(10)             NOT NULL,
    address        VARCHAR(60)             NOT NULL,
    user_type      user_type               NOT NULL
);

CREATE TABLE orders
(
    order_id    SERIAL PRIMARY key,
    user_id     VARCHAR(30) REFERENCES users (ID_card_number) NOT NULL,
    order_Value integer                                       NOT NULL,
    item_amount integer                                       NOT NULL
);

create table orders_help
(
    items    serial primary key,
    order_id integer REFERENCES orders (order_id) not null,
    item_id  integer references storage (item_id) not null
);



INSERT INTO users(ID_card_number, forename, lastName, email, password, country, city, zip_code, address, user_type)
values ('099396DE', 'Norbert', 'Zaja', 'zaja.norbert@gmail.com', 'Admin12345', 'Hungary', 'Markaz', '3262',
        'Fő utca 145', 'ADMIN');
INSERT INTO storage(brand, NR, element, amount, price, pic)
VALUES ('Blizzard', 'FIREBIRD COMPETITION 76+TPX 12', 'SKI', 3, 300,
        'https://www.blizzardsports.com/files/10221/c-fit-w-2032-q-auto-eco8A9042AA001_FIREBIRD-COMPETITION-76-_-TPX12-DEMO.png');
INSERT INTO storage(brand, NR, element, amount, price, pic)
VALUES ('Blizzard', 'FIREBIRD RACE TI+TPX 12 ', 'SKI', 2, 250,
        'https://www.blizzardsports.com/files/10228/c-fit-w-2032-q-auto-eco8A9038AA001_FIREBIRD-RACE-TI_-_-TPX-12-DEMO.png');
INSERT INTO storage(brand, NR, element, amount, price, pic)
VALUES ('Blizzard', 'FIREBIRD SL FIS (FLAT+PLATE)', 'SKI', 4, 250,
        'https://www.blizzardsports.com/files/7290/c-fit-w-2032-q-auto-eco8A801200001_FIREBIRD-SL-FIS-_FLAT_PLATE_.png');
INSERT INTO storage(brand, NR, element, amount, price, pic)
VALUES ('Head', 'Various Boa MIPS', 'HELMET', 3, 120, 'https://cdn-mdb.head.com/CDN/D/324149/1/1300.jpg');
INSERT INTO storage(brand, NR, element, amount, price, pic)
VALUES ('Head', 'RADAR', 'HELMET', 2, 120, 'https://cdn-mdb.head.com/CDN/D/323409/1/1300.jpg');
INSERT INTO storage(brand, NR, element, amount, price, pic)
VALUES ('Head', 'Rita', 'HELMET', 5, 95, 'https://cdn-mdb.head.com/CDN/D/323709/1/1300.jpg');
INSERT INTO accessory(accessory_id, length, flex_index)
values (1, 150, 13);
INSERT INTO accessory(accessory_id, length, flex_index)
values (2, 148, 11);
INSERT INTO accessory(accessory_id, length, flex_index)
values (3, 156, 13);
INSERT INTO wear(wear_id, size)
VALUES (1, 'M/L');
INSERT INTO wear(wear_id, size)
VALUES (2, 'XS/S');
INSERT INTO wear(wear_id, size)
VALUES (3, 'XL');
INSERT INTO storage_accessory(storage_id, accessory)
values (1, 1);
INSERT INTO storage_accessory(storage_id, accessory)
values (2, 2);
INSERT INTO storage_accessory(storage_id, accessory)
values (3, 3);
INSERT INTO storage_wear(storage_id, wear_id)
values (4, 1);
INSERT INTO storage_wear(storage_id, wear_id)
values (5, 2);
INSERT INTO storage_wear(storage_id, wear_id)
values (6, 3);


CREATE OR REPLACE FUNCTION notEmpty() RETURNS TRIGGER AS '
    BEGIN
        IF NEW.amount < 0 THEN
            RAISE ''Please check the storage'';
        END IF;
        RETURN NULL;
    END
' LANGUAGE PLPGSQL;

CREATE TRIGGER notMinus
    AFTER UPDATE OR INSERT ON storage
    FOR EACH ROW EXECUTE PROCEDURE notEmpty();