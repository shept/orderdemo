-- create schema s1;	-- needed for apgdiff
create table s1.Address (id  serial not null, addressType int4, city varchar(255), country varchar(255), number varchar(255), street varchar(255), version int4, zipcode varchar(255), customer_id int4, primary key (id));
create table s1.Customer (id  serial not null, birthdate date, firstName varchar(255), image bytea, male bool, name varchar(255), version int4, primary key (id));
create table s1.Item (id  serial not null, itemnumber varchar(255), name varchar(255), price int4, price1 numeric(19, 2), quantity float8, unit_id int4 not null, vatrate_id int4 not null, version int4, primary key (id));
create table s1.Order (id  serial not null, date timestamp, version int4, customer_id int4, number_id int4, primary key (id));
create table s1.OrderItem (id  serial not null, item_id int4, name varchar(255), quantity int4, version int4, order_id int4, primary key (id));
create table s1.OrderNumber (id  serial not null, primary key (id));
create table s1.Unit (id  serial not null, name varchar(255), version int4, primary key (id));
create table s1.Vatrate (id  serial not null, rate int4, version int4, primary key (id));
alter table s1.Address add constraint fk_address_customer foreign key (customer_id) references s1.Customer;
alter table s1.Item add constraint fk_item_vatrate foreign key (vatrate_id) references s1.Vatrate;
alter table s1.Item add constraint fk_item_unit foreign key (unit_id) references s1.Unit;
alter table s1.Order add constraint fk_order_customer foreign key (customer_id) references s1.Customer;
alter table s1.Order add constraint fk_order_number foreign key (number_id) references s1.OrderNumber;
alter table s1.OrderItem add constraint fk_orderitem_item foreign key (item_id) references s1.Item;
alter table s1.OrderItem add constraint fk_orderitem_order foreign key (order_id) references s1.Order;
