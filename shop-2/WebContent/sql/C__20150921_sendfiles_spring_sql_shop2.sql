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
'����� ���ʱ�','userid01@aaa.bbb','����','80/01/01');

insert into useraccount values 
('userid02','username02','1234','222-222',
'��⵵ ������','userid02@aaa.bbb','����','85/01/01');

insert into useraccount values 
('userid03','username03','1234','222-222',
'������ ������','userid03@aaa.bbb','��ȹ','83/02/02');

select * from userAccount;