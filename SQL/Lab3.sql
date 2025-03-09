---------------Lab3--------------
use SteamStore
select * from Recenzii;
select * from DateJocuri;
select * from Clienti;
select * from Tranzactii;
select * from Rol;

insert into DateJocuri
values('Team Fortress 2','Valve',2007,'Valve');
insert into DateJocuri
values('Factorio Space Age','Wube_Software',2024,'Wube_Software'),
('Pixel Privateers','Re-Logic',2017,'Re-Logic'),
('Hyper Demon','Sorath',2022,'Sorath')
insert into DateJocuri
values('Dota 2','Valve',2013,'Valve')

insert into Tranzactii
values(3,2),
(3,1),
(3,6),
(5,5),
(5,6),
(5,7),
(5,8),
(5,10),
(8,11),
(8,13),
(8,4);

insert into Recenzii
values('Amazing (30hours of gameplay not 3)' ,6),
('This game is awesome.' ,6),
('Amazing' ,6),
('Great game' ,7),
('Awesome' ,7),
('A nice horror game',7),
('Addicting' ,4),
('500h still not done' ,4),
('17 years later still kicking' ,10),
('Best team shooter' ,10),
('Idk whats happening' ,13),
('High Score' ,13),
('For an alpha is pretty great' ,3),
('haze press 4' ,3);
--------------------------------

----------------------SELECTS USING INNER JOINS AND SIMILAR OPPERATIONS----------------------------
select nume_joc from DateJocuri where id_joc=2
union
select nume from Clienti where id_Client=2

select distinct D.nume_joc from DateJocuri D
inner join Tranzactii T on D.id_joc = T.id_joc
inner join Clienti C on T.id_client = C.id_Client


select D.nume_joc,D.id_joc,C.nume from DateJocuri D
inner join Tranzactii T on D.id_joc = T.id_joc
inner join Clienti C on T.id_client = C.id_Client
where C.nume='Gabe' and C.id_Client =1

select C.id_Client,C.nume,D.nume_joc from DateJocuri D
inner join Tranzactii T on D.id_joc = T.id_joc
right outer join Clienti C on T.id_client = C.id_Client

select D.developer, count (*) as nr_jocuri_developed
from DateJocuri D
group by developer
order by nr_jocuri_developed desc

select C.id_rol,count(*) as persoane_cu_rol
from Clienti C
group by id_rol
order by count(*) desc;

select r.id_joc  ,count(id_joc) as numar_rec
from Recenzii r
group by id_joc having count(id_joc) >= 2


