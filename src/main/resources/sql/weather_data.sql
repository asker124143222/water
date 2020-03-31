create table weather_data
(
    weather_year int not null,
    avg_value    int not null,
    id           int auto_increment
        primary key,
    userid       int not null
);
