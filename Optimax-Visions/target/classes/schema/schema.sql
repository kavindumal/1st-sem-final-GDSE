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

create table customer
(
    customerId   varchar(10) not null
        primary key,
    customerName varchar(50) null,
    telNo        int         null,
    employeeId   varchar(10) not null,
    constraint employeeId
        foreign key (employeeId) references employee (empId)
);

create table salary
(
    salaryId    varchar(10) not null,
    dateOfGiven date        not null,
    otHours     int         null,
    totalAmount double      null,
    constraint salaryId
        primary key (salaryId)
);

create table employee
(
    empId       varchar(10)  not null,
    empName     varchar(50)  not null,
    empAddress  varchar(100) not null,
    empTel      int          not null,
    dob         date         not null,
    otHours     int          not null,
    basicSalary int          not null,
    salaryId    varchar(10)  not null,
    constraint employee_pk
        primary key (empId),
    constraint salaryId_fk
        foreign key (salaryId) references salary (salaryId)
);

create table doctor
(
    doctorId      varchar(10)  not null,
    name          varchar(50)  not null,
    telNo         int          not null,
    dob           date         not null,
    address       varchar(100) not null,
    availableTime time         not null,
    otHours       int          not null,
    basicSalary   double       not null,
    salaryId      varchar(10)  not null,
    constraint doctor_pk
        primary key (doctorId),
    constraint doctor_salary_salaryId_fk
        foreign key (salaryId) references salary (salaryId)
);

create table patient
(
    patientId        varchar(10) not null,
    name             varchar(50) not null,
    age              int         not null,
    detailsOfPatient text        not null,
    constraint patient_pk
        primary key (patientId)
);

create table appointment
(
    appointmentId varchar(10) not null,
    time          time        not null,
    column_name   int         not null,
    date          date        not null,
    doctorId      varchar(10) not null,
    constraint appointment_pk
        primary key (appointmentId),
    constraint doctorId
        foreign key (doctorId) references doctor (doctorId),
    constraint patientId
        foreign key (appointmentId) references patient (patientId)
);

create table prescription
(
    prescriptionId  varchar(10) not null,
    sph             double      not null,
    cyl             double      not null,
    axis            int         not null,
    prism           double      not null,
    base            double      not null,
    pd              double      not null,
    dateLastChecked date        not null,
    constraint prescription_pk
        primary key (prescriptionId)
);

create table prescriptionglass
(
    glassId        varchar(10) not null
        primary key,
    lenseName      varchar(50) not null,
    totalPrice     double      not null,
    prescriptionId varchar(10) not null,
    frameId        varchar(10) not null,
    constraint frame_fk
        foreign key (frameId) references frame (frameId),
    constraint prescription_fk
        foreign key (prescriptionId) references prescription (prescriptionId)
);

create table frame
(
    frameId    varchar(10) not null,
    franmeName varchar(50) not null,
    constraint frame_pk
        primary key (frameId)
);

create table eyeGlass
(
    eyglassId varchar(10) not null,
    glassName varchar(50) not null,
    brand     varchar(50) not null,
    qty       int         not null,
    price     double      not null,
    constraint eyeGlass_pk
        primary key (eyglassId)
);

create table customerGlass
(
    customerId varchar(10) not null,
    glassId    varchar(10) not null,
    constraint customer_fk
        foreign key (customerId) references customer (customerId),
    constraint eyeglass_fk
        foreign key (glassId) references eyeglass (eyglassId)
);

create table frameOfPrescription
(
    frameId        varchar(10) not null,
    prescriptionId varchar(10) not null,
    constraint frames_fk
        foreign key (frameId) references frame (frameId),
    constraint prescriptions_fk
        foreign key (prescriptionId) references prescriptionglass (prescriptionId)
);