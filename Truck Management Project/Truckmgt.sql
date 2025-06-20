create database truckmgt;

use truckmgt;
create table truck(
truckNo varchar(15) Primary Key,
vehicleName varchar(30),
capacity int,
driverName varchar(30)
);

create table admin(
username Varchar (30),
password varchar(30)
);
insert into admin values("admin","123");
select * from admin;

select * from truck;
insert into truck values("123","wer","12","hello");


