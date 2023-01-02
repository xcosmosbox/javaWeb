--  dept table
drop table if exists dept;

create table dept(
    deptno int,
    dname varchar(255),
    loc varchar(255)
);

insert into dept(deptno, dname, loc) VALUES (10, 'sale', 'mel');
insert into dept(deptno, dname, loc) VALUES (20, 'dev', 'sydney');
insert into dept(deptno, dname, loc) VALUES (30, 'tech', 'city');
insert into dept(deptno, dname, loc) VALUES (40, 'media', 'clayton');

commit;

select * from dept;