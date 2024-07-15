create table user (
    id bigint not null auto_increment,
    name varchar(200) not null,
    email varchar(300) not null unique,
    password varchar(300) not null,
    role varchar(100) not null,
    active tinyint not null,
    primary key (id)
);