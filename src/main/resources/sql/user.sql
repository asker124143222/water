create table user
(
    userId      int auto_increment
        primary key,
    createTime  datetime     null,
    email       varchar(255) null,
    expiredDate date         null,
    name        varchar(255) not null,
    password    varchar(255) not null,
    salt        varchar(255) null,
    state       tinyint      not null,
    tel         varchar(255) null,
    userName    varchar(255) not null,
    constraint UK_hl8fftx66p59oqgkkcfit3eay
        unique (userName)
);
