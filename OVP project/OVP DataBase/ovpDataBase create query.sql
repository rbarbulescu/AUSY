CREATE DATABASE ovpDataBase;
USE ovpdatabase;

CREATE TABLE user(
	id INT AUTO_INCREMENT PRIMARY KEY,
	userName VARCHAR(50) UNIQUE,
    password VARCHAR(50)
);

CREATE TABLE user_type (
    userId INT PRIMARY KEY,
    userType VARCHAR(25),
    FOREIGN KEY (userId)
        REFERENCES ovpdatabase.user (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);