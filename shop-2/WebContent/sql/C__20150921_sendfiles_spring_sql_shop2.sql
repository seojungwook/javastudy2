--/shop-2/sql/shop2.sql
create table userAccount (
   userid varchar2(20) primary key,
   username varchar2(20),
   password varchar2(200),
   postcode varchar2(8),
   address varchar2(50),
   email varchar2(50),
   job  varchar2(30),
   birthday date
)
insert into useraccount values 
('userid01','username01','1234','111-111',
'서울시 서초구','userid01@aaa.bbb','영업','80/01/01');

insert into useraccount values 
('userid02','username02','1234','222-222',
'경기도 성남시','userid02@aaa.bbb','관리','85/01/01');

insert into useraccount values 
('userid03','username03','1234','222-222',
'강원도 강릉시','userid03@aaa.bbb','기획','83/02/02');

select * from userAccount;