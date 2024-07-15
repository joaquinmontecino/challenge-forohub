create table course (
    id bigint not null AUTO_INCREMENT,
    course_name varchar(200) not null,
    category varchar(100) not null,
    active tinyint not null,
    primary key(id)
);
