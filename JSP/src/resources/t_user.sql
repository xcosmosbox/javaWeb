use SRS;
--  t_user table
drop table if exists t_user;

create table t_user(
    id int primary key auto_increment,
    username varchar(255),
    password varchar(255)
);

insert into t_user(username, password) VALUES ('admin','123456');
insert into t_user(username, password) VALUES ('root','456789');


commit;

select * from t_user;