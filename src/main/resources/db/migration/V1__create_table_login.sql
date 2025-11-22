create table login(
    id bigint not null auto_increment,
    username varchar(50) not null,
    email varchar(50) not null,
    password varchar(50) not null,
    primary key(id)
);
