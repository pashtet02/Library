CREATE TABLE `library`.`users` (
                                       `id` INT NOT NULL AUTO_INCREMENT,
                                       `username` VARCHAR(45) NOT NULL,
                                       `password` TEXT(512) NOT NULL,
                                       `mail` VARCHAR(45) NULL,
                                       `fine` INT ZEROFILL NULL,
                                       `role` ENUM('USER', 'LIBRARIAN', 'ADMIN') NOT NULL,
                                       PRIMARY KEY (`id`),
                                       UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                       UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);


insert into users (username, password, mail, fine, role)
values ('Pashtet', '1111', 'chodak2014ua@gmail.com', 8, 'LIBRARIAN')
-- write your database creation script here﻿

CREATE TABLE `library`.`books` (
                                   `id` INT NOT NULL AUTO_INCREMENT,
                                   `title` VARCHAR(64) NOT NULL,
                                   `author` VARCHAR(64) NOT NULL,
                                   `ISBN` VARCHAR(13) NOT NULL,
                                   `publisher` VARCHAR(64) NULL,
                                   `publishingDate` DATE NULL,
                                   `number` INT ZEROFILL NULL,
                                   `language` VARCHAR(45) NULL,
                                   PRIMARY KEY (`id`),
                                   UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE,
                                   UNIQUE INDEX `ISBN_UNIQUE` (`ISBN` ASC) VISIBLE);