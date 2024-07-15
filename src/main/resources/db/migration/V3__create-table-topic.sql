create table topic(
    id bigint not null auto_increment,
    title varchar(255) not null,
    message text not null,
    creation_date datetime not null,
    status varchar(50) not null,
    author_id bigint not null,
    course_id bigint not null,
    primary key(id),
    foreign key(author_id) references user(id),
    foreign key(course_id) references course(id)
);