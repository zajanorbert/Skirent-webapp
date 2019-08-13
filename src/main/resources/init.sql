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
DROP TABLE IF EXISTS arguments CASCADE;

drop type if exists element;
drop type if exists user_type;
CREATE TYPE element AS ENUM ('SKI', 'SKIBOOT', 'SKISTICK', 'SNOWBOARD', 'BOARDBOOT', 'JACKET', 'TROUSERS', 'HELMET', 'GLOVE', 'GOGGLESS');
CREATE TYPE user_type AS ENUM ('USER', 'ADMIN');

CREATE TABLE storage
(
    item_id serial NOT NULL PRIMARY KEY,
    brand VARCHAR(30) NOT NULL,
    NR VARCHAR(30) NOT NULL,
    element element NOT NULL,
    amount integer,
    price integer,
    pic varchar(60)
);

create table arguments(
  item_id integer references storage(item_id),
  size varchar(3),
  length integer,
  flex_index integer
);

CREATE TABLE users
(
    ID_card_number VARCHAR(30) PRIMARY KEY NOT NULL,
    forename VARCHAR(30) NOT NULL,
    lastName VARCHAR(30) NOT NULL,
    email VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    country varchar(30) not null,
    city VARCHAR(20) NOT NULL,
    zip_code varchar(10) NOT NULL,
    address VARCHAR(60) NOT NULL,
    user_type user_type NOT NULL
);

CREATE TABLE orders
(
    order_id SERIAL PRIMARY key,
    user_id VARCHAR(30) REFERENCES users(ID_card_number) NOT NULL,
    order_Value integer NOT NULL,
    item_amount integer NOT NULL
);

create table orders_help
(
    items serial primary key,
    order_id integer REFERENCES orders(order_id) not null,
    item_id integer references storage(item_id) not null
);

INSERT INTO users(ID_card_number, forename, lastName, email, password, country, city, zip_code, address, user_type) values ('099396DE', 'Norbert', 'Zaja', 'zaja.norbert@gmail.com', 'Admin12345', 'Hungary', 'Markaz', '3262', 'Fő utca 145', 'ADMIN');