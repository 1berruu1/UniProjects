use SteamStore;

---------------------INSERT--------------------------
Insert into [DateJocuri]
VALUES 
('Blasphemy','The_Game_chicken',2019,'Team17'),
('Signalis','Rose-engine',2022,'Humble_Games'),
('Deadlock','Valve',2025,'Valve'),
('Terraria','Re-Logic',2011,'Re-logic'),
('Factorio','Wube_Software',2020,'Wube_Software'),
('Hollow Knight','Team_Cherry',2017,'Team_Cherry'),
('Dark_Wood','Acid_Wizard_Studios',2017,'Acid_Wizard_Studios'),
('DevilDaggers','Sorath',2016,'Sorath'),
('Test',Null,2024,'aaaaa');

Insert into Rol
Values
('Developer'),
('Member'),
('Moderator'),
('Valve_Dev');

Insert into Clienti
Values
('Gabe',4),
('Berruu',2),
('Jastro',2),
('Rose-Engine',1),
('Simi',2),
('John',3),
('Yoshi',4),
('Skinwalker',2),
('User125234',2)


Insert into Tranzactii
Values
(1,2),
(1,3),
(2,1),
(2,3),
(4,5),
(7,2),
(4,1);

Insert into Recenzii
Values
('Very good game would recommend', 1),
('Incredible',1),
('Whee silk song', 5),
('Very addicting',4),
('Making factories = good',4);
-------------------------------------------------------------

------------------------SELECT-------------------------------

select * from Recenzii;
select * from Clienti;
select * from DateJocuri;
select * from Tranzactii;
select * from Rol;

---------------------------------------------------------------



--------------------------DELETE/UPDATE------------------------

delete from DateJocuri where id_j=3 or id_j=7;
update DateJocuri set nume_joc = 'Blasphemous' where id_j = 15;
update Recenzii set description = 'description' where id_j = 1;
update Recenzii set description = 'Very good game would recommend' where id_r = 1;
delete from DateJocuri where developer is Null;
delete from DateJocuri where id_j = 2;
select * from DateJocuri where nume_joc is not null and id_j = 3 
select * from DateJocuri where nume_joc is not null and id_j not in (select id_j from Tranzactii) 

---------------------------------------------------------------

