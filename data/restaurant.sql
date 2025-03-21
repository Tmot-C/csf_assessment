-- IMPORTANT: DO NOT CHANGE THE GIVEN SCHEMA UNLESS YOU HAVE A GOOD REASON
-- IF YOU DO CHANGE IT WRITE THE JUSTIFICATION IN A COMMENT ABOVE THE CHANGE

drop database if exists restaurant;

create database restaurant;

use restaurant;

create table customers (
  -- username changed to have unique constraint due to the fact that another database references it as a foreign key
  -- so as to maintain database integrity
  username varchar(64) not null unique,
  password varchar(128) not null
);

insert into customers(username, password) values
  ('fred', sha2('fred', 224)),
  ('barney', sha2('barney', 224)),
  ('wilma', sha2('wilma', 224)),
  ('betty', sha2('betty', 224)),
  ('pebbles', sha2('pebbles', 224));

-- TODO: Task 1.2
-- Write your task 1.2 below
CREATE TABLE IF NOT EXISTS place_orders(
    order_id CHAR(8) not null primary key,
    payment_id VARCHAR(128) not null unique,
    order_date DATE,
    total DECIMAL(15,2) not null,
    username VARCHAR(128) not null,
    constraint fk_username foreign key(username) references customers(username) on delete cascade
);