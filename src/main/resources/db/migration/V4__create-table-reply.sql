create table reply(
    id bigint not null auto_increment,
    message text not null,
    topic_id bigint not null,
    creation_date datetime not null,
    author_id bigint not null,
    deleted tinyint not null,
    solution tinyint not null,
    primary key(id),
    foreign key(author_id) references user(id),
    foreign key(topic_id) references topic(id)
);