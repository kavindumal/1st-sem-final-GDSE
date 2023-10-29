drop database if exists visioncare;

create database visionCare;

use visionCare;

create table user
(
    username varchar(20)  not null
        primary key,
    password varchar(100) not null,
    email    varchar(50)  not null
);

