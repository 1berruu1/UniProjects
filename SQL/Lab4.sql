
-------------Lab4------------
use SteamStore
select * from Recenzii;
select * from DateJocuri;
select * from Clienti;
select * from Tranzactii;
select * from Rol;
-------------------------------

-------------------FUNCTIONS AND STOCKED PROCEDUES--------------------------------
--Ex1

create or alter procedure AddJoc
(@nume_joc varchar(50),@developer varchar(50),@an_lansare int,@publisher varchar(50))
as begin
if dbo.ExistaJoc(@nume_joc) = 1
begin
raiserror('Jocul deja exista',10,1)
end
else
insert into DateJocuri
values(@nume_joc,@developer,@an_lansare,@publisher)
end


exec AddJoc 'test5','testdev',2025,'Testpub'



create or alter procedure UpdateRecenzie
(@id int,@updated_review varchar(50))
as begin
if dbo.ExistsIdReview = 1
begin
update Recenzii
set description = @updated_review where @id = id_recenzii
end
else 
raiserror('id-ul nu exista',10,1)
end

exec UpdateRecenzie 2,'emotional'

create or alter procedure addRecenzie
(@review varchar(50),@id_joc int)
as begin
if dbo.ExistsIdGame(@id_joc) = 0
begin
raiserror('Jocul nu exista',10,1)
end
else 
insert into Recenzii
values (@review,@id_joc)
end

exec addRecenzie 'Banger Ost',2100


create or alter procedure addRol 
(@nume varchar(50))
as begin
if dbo.CheckRoleExists(@nume) = 1
begin 
raiserror ('Rolul deja exista',10,1)
end
else
insert into Rol
values(@nume)
end


exec addRol 'Helper'
exec addRol 'test2'



create or alter function ExistaJoc(@nume_joc varchar(50))
returns bit 
as begin
declare @exist as bit;
select @exist = case when count(*) > 0  then 1 else 0 
end
from DateJocuri
where nume_joc = @nume_joc
return @exist;
end

create or alter function ExistsIdReview (@id int)
returns bit 
as begin 
declare @exists as bit;
select @exists = case when count(*) > 0 then 1 else 0
end
from Recenzii
where id_recenzii = @id 
return @exists
end


create or alter function ExistsIdGame(@id int)
returns bit
as begin
declare @exists as bit
select @exists = case when count(*) > 0 then 1 else 0 end
from DateJocuri
where id_joc = @id;
return @exists;
end

create or alter function CheckRoleExists(@nume_rol varchar(50))
returns bit 
as begin 
declare @exists as bit
select @exists = case when count(*) > 0 then 1 else 0 end
from Rol
where nume_r = @nume_rol
return @exists;
end
----------ex2-----------

create or alter view Jocuri
as select D.id_joc, nume_joc,id_tranzactie,nume from DateJocuri D,Tranzactii T,Clienti C
where D.id_joc = T.id_joc and T.id_client = C.id_Client


select * from Jocuri



------------------TRIGGERS------------------------
--------ex3-------


create or alter trigger trg_DateJocuri_Insert
on DateJocuri
after insert
as
begin
    declare @currentDateTime datetime;
    set @currentDateTime = getdate();

    print 'Type of Operation: INSERT';
    print 'Table: DateJocuri';
    print 'Date and Time: ' + cast(@currentDateTime as nvarchar);
end;

create or alter trigger trg_DateJocuri_Delete
on DateJocuri
after delete
as
begin
    declare @currentDateTime datetime;
    set @currentDateTime = getdate();

    print 'Type of Operation: DELETE';
    print 'Table: DateJocuri';
    print 'Date and Time: ' + cast(@currentDateTime as nvarchar);
end;


insert into DateJocuri
values('test','test',2026,'test')


select * from DateJocuri

delete from DateJocuri
where id_joc =19
