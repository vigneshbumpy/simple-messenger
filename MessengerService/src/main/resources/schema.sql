DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
                      id INT AUTO_INCREMENT  PRIMARY KEY,
                      user_name VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL
);