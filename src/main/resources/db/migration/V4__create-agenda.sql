create table users(
    id bigint not null auto_increment,
    senha varchar(255) not null,
    email varchar(100) not null unique,

    primary key(id)
);