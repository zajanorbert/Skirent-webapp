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

DROP TABLE IF EXISTS buyers cascade;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS storage CASCADE;

drop type if exists delivery;
CREATE TYPE delivery AS ENUM ('TWO_WAY', 'ONE_WAY', 'INSTORE');

drop type if exists element;
CREATE TYPE element AS ENUM ('SKI', 'SKIBOOT', 'SKISTICK', 'SNOWBOARD', 'BOARDBOOT', 'JACKET', 'TROUSERS', 'HELMET', 'GLOVE', 'GOGGLESS');

CREATE TABLE storage
(
    item_id integer NOT NULL PRIMARY KEY,
    brand VARCHAR(30) NOT NULL,
    NR VARCHAR(30) NOT NULL,
    element element NOT NULL,
    amount integer,
    price integer
);

create table arguments(
  item_id integer references storage(item_id),
  size varchar(3),
  length integer,
  flex_index integer
);

CREATE TABLE buyers
(
    ID_card_number VARCHAR(10) PRIMARY KEY NOT NULL,
    forename VARCHAR(30) NOT NULL,
    lastName VARCHAR(30) NOT NULL,
    email_address VARCHAR(30) NOT NULL,
    city VARCHAR(20) NOT NULL,
    postal_code varchar(10) NOT NULL,
    address VARCHAR(60) NOT NULL
);

CREATE TABLE orders
(
    order_id SERIAL PRIMARY key,
    items integer references orders_help(items) not null,
    buyer_id VARCHAR(30) REFERENCES buyers(ID_card_number) NOT NULL,
    begin_date date NOT NULL,
    end_date date NOT NULL,
    order_Value integer NOT NULL,
    item_amount integer NOT NULL
);

create table orders_help
(
    items serial primary key,
    order_id integer REFERENCES orders(order_id) not null,
    item_id integer references storage(item_id) not null,
    delivery delivery NOT NULL
);

INSERT INTO buyers(ID_card_number, forename, lastName, city, email_address, postal_code, address) values ('099396DE', 'Norbert', 'Zaja', 'zaja.norbert@gmail.com', 'Markaz', '3262', 'Fő utca 145');