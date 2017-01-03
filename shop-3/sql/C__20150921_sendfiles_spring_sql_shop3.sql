create table sale 
(
  saleid number(10),
  userid varchar2(20),
  updatetime date,
  primary key (saleid, userid)
)

create table saleline 
(
   saleid number(10),
   salelineid number(5),
   itemid number(5),
   quantity number(3),
   updatetime date,
   primary key (saleid, salelineid));
select * from sale;
select * from saleline;

select * from board

create table member (
   id varchar2(20) primary key,
   pass varchar2(30),
   name varchar2(10),
   birthDay Date,
   gender varchar2(5),
   email varchar2(50),
   pictureUrl varchar2(100)
)   
alter table member add postcode varchar2(7);
alter table member add address varchar2(50);
select * from member
drop table sale;
drop table saleline;
drop table member;

create table student_cipher as select *  from student_cipher;

alter table student_cipher modify jumin varchar2(200);

desc student_cipher;

drop table student_cipher;
