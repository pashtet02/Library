-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- Schema library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `library3` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `library3`;

-- -----------------------------------------------------
-- Table `library3`.`books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library3`.`books`
(
    `id`             INT          NOT NULL AUTO_INCREMENT,
    `title`          VARCHAR(64)  NOT NULL,
    `author`         VARCHAR(64)  NOT NULL,
    `ISBN`           VARCHAR(13)  NOT NULL,
    `publisher`      VARCHAR(64)  NULL DEFAULT NULL,
    `publishingDate` DATE         NULL DEFAULT NULL,
    `number`         INT UNSIGNED NULL DEFAULT NULL,
    `language`       VARCHAR(45)  NULL DEFAULT NULL,
    `image`          VARCHAR(128) NULL DEFAULT NULL,
    `description_ua` VARCHAR(512) NULL DEFAULT NULL,
    `description_en` VARCHAR(512) NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE,
    UNIQUE INDEX `ISBN_UNIQUE` (`ISBN` ASC) VISIBLE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 109
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library3`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library3`.`users`
(
    `id`         INT                                 NOT NULL AUTO_INCREMENT,
    `username`   VARCHAR(45)                         NOT NULL,
    `password`   VARCHAR(72)                         NOT NULL,
    `mail`       VARCHAR(45)                         NOT NULL,
    `fine`       DECIMAL(7, 2) UNSIGNED              NULL DEFAULT NULL,
    `role`       ENUM ('USER', 'LIBRARIAN', 'ADMIN') NOT NULL,
    `isBanned`   TINYINT(3) UNSIGNED ZEROFILL        NOT NULL,
    `userLocale` VARCHAR(2)                          NULL DEFAULT NULL,
    `firstName`  VARCHAR(45)                         NULL DEFAULT NULL,
    `secondName` VARCHAR(45)                         NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
    UNIQUE INDEX `mail_UNIQUE` (`mail` ASC) VISIBLE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 31
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library3`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library3`.`orders`
(
    `id`               INT                                                                                  NOT NULL AUTO_INCREMENT,
    `user_id`          INT                                                                                  NOT NULL,
    `book_id`          INT                                                                                  NOT NULL,
    `startDate`        TIMESTAMP(5)                                                                         NOT NULL,
    `returnDate`       DATE                                                                                 NULL DEFAULT NULL,
    `status`           ENUM ('RESERVED', 'APPROVED', 'READING_HALL', 'REFUSED', 'UNTERMINATED', 'RETURNED') NOT NULL,
    `userComment`      VARCHAR(250)                                                                         NULL DEFAULT NULL,
    `librarianComment` VARCHAR(250)                                                                         NULL DEFAULT NULL,
    PRIMARY KEY (`id`, `user_id`, `book_id`),
    INDEX `fk_orders_users_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_orders_books1_idx` (`book_id` ASC) VISIBLE,
    CONSTRAINT `fk_orders_users`
        FOREIGN KEY (`user_id`)
            REFERENCES `library3`.`users` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_orders_books1`
        FOREIGN KEY (`book_id`)
            REFERENCES `library3`.`books` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 57
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library3`.`reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library3`.`reviews`
(
    `id`           INT          NOT NULL AUTO_INCREMENT,
    `user_id`      INT          NOT NULL,
    `book_id`      INT          NOT NULL,
    `mark`         TINYINT      NOT NULL,
    `user_comment` VARCHAR(512) NOT NULL,
    PRIMARY KEY (`id`, `user_id`, `book_id`),
    INDEX `fk_reviews_books1_idx` (`book_id` ASC) VISIBLE,
    INDEX `fk_reviews_users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_reviews_books1`
        FOREIGN KEY (`book_id`)
            REFERENCES `library3`.`books` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT `fk_reviews_users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `library3`.`users` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 8
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
