CREATE TABLE Users
(
    id       INTEGER NOT NULL AUTO_INCREMENT,
    username NVARCHAR(30) NOT NULL,
    password NVARCHAR(256) NOT NULL,
    primary key(id)
);