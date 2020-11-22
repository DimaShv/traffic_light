create sequence hibernate_sequence start 1 increment 1;
create table crossroad_log (
    id int8 not null,
    car_id uuid not null,
    direction varchar(255) not null,
    is_traffic_offender boolean not null,
    time timestamp not null,
    primary key (id)
);