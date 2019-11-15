CREATE DATABASE IF NOT EXISTS ovpdb;
USE ovpdb;

CREATE TABLE IF NOT EXISTS admins (
  adminId VARCHAR(100),
  institution VARCHAR(100) NOT NULL,
  PRIMARY KEY (adminId)
  )ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS persons (
  id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(100) NOT NULL,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL,
  typeofuser VARCHAR(20) NOT NULL,
  adminsId VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX username (username ASC) VISIBLE,
  INDEX fk_persons_admins1_idx (adminsId ASC) VISIBLE,
  CONSTRAINT fk_persons_admins1
	FOREIGN KEY (adminsId)
	REFERENCES ovpdb.admins (adminId) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS students (
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL,
  phone VARCHAR(15) NOT NULL,
  address VARCHAR(100) NOT NULL,
  CNP VARCHAR(13),
  birthday DATE NOT NULL,
  trips INT NULL DEFAULT NULL,
  personsId VARCHAR(100) NOT NULL,
  PRIMARY KEY (CNP),
  INDEX fk_students_persons1_idx (personsId ASC) VISIBLE,
  CONSTRAINT fk_students_persons1
    FOREIGN KEY (personsId)
    REFERENCES ovpdb.persons (username) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB;
    

