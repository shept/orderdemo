-- Create Role, Database, e.t.c


-- DROP ROLE sheptorder_group;

CREATE ROLE sheptorder_group
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE;

-- Role: "sheptorder"

-- DROP ROLE sheptorder;

CREATE ROLE sheptorder LOGIN
  ENCRYPTED PASSWORD 'md54787ea84601584999b72f955dc1ac726'
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE;
GRANT sheptorder_group TO sheptorder;

CREATE DATABASE sheptorder
  WITH ENCODING='UTF8';

CREATE SCHEMA s1
  AUTHORIZATION postgres;
GRANT ALL ON SCHEMA s1 TO postgres;
GRANT USAGE on SCHEMA s1 to sheptorder_group;

-- we need language for store procedures
CREATE LANGUAGE plpgsql;


