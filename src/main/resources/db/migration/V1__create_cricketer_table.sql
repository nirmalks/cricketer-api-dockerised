drop table if exists cricketers cascade;
drop sequence if exists cricketer_id_seq;
create sequence cricketer_id_seq start 1 increment 50;
create table cricketers (id int8 default nextval('cricketer_id_seq') not null, country varchar(255) not null, highest_score bytea, name varchar(255) not null, primary key (id));