
create database SteamStore;
use SteamStore;

drop table DateJocuri; 

create table DateJocuri
( 
id_joc int not null primary key identity(1,1),
nume_joc nvarchar(50) ,
developer nvarchar(50),
an_lansare int,
publisher nvarchar(50),

);
drop table Clienti;

create table Clienti(
id_Client int not null primary key identity(1,1),
nume nvarchar(50), 
id_rol int,
foreign key (id_rol) references Rol(id_rol)
on delete cascade 
on update cascade
);

drop table Tranzactii;

create table Tranzactii
(
id_tranzactie int not null primary key identity(1,1),
id_client int,
id_joc int,
foreign key (id_joc) references DateJocuri(id_joc)
on delete cascade
on update cascade,
foreign key (id_client) references Clienti(id_client)
on delete cascade
on update cascade
);

drop table Recenzii;

create table Recenzii
(
id_recenzii int not null primary key identity(1,1),
description nvarchar(50),
id_joc int
foreign key  (id_joc) references DateJocuri(id_joc)
on delete cascade
on update cascade
);

Drop table Rol;
create table Rol
(
nume_r nvarchar(50),
id_rol int primary key identity(1,1)
);
