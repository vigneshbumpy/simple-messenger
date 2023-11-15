DROP TABLE IF EXISTS Users;
CREATE TABLE Users
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS LoggedInUsers;
CREATE TABLE LoggedInUsers
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS Messages;
CREATE TABLE Messages
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    from_user_name VARCHAR(255)  NOT NULL,
    to_user_name   VARCHAR(255)  NOT NULL,
    text           VARCHAR(1024) NOT NULL,
    is_read        BOOLEAN
);