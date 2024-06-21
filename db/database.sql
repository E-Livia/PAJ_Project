-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema calendar_app
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema calendar_app
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `calendar_app` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `calendar_app` ;

-- -----------------------------------------------------
-- Table `calendar_app`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `calendar_app`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `birthdate` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username` (`username` ASC) VISIBLE,
  UNIQUE INDEX `unique_email` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `calendar_app`.`locations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `calendar_app`.`locations` (
  `location_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `latitude` DECIMAL(10,8) NULL DEFAULT NULL,
  `longitude` DECIMAL(11,8) NULL DEFAULT NULL,
  PRIMARY KEY (`location_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `calendar_app`.`events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `calendar_app`.`events` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL DEFAULT NULL,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(1000) NULL DEFAULT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `location_id` INT NULL DEFAULT NULL,
  `start_time` TIME NULL DEFAULT NULL,
  `end_time` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `location_id` (`location_id` ASC) VISIBLE,
  CONSTRAINT `events_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `calendar_app`.`users` (`user_id`),
  CONSTRAINT `events_ibfk_2`
    FOREIGN KEY (`location_id`)
    REFERENCES `calendar_app`.`locations` (`location_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `calendar_app` ;

-- -----------------------------------------------------
-- procedure AddUser
-- -----------------------------------------------------

DELIMITER $$
USE `calendar_app`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `AddUser`(
    IN p_username VARCHAR(50),
    IN p_password VARCHAR(100),
    IN p_email VARCHAR(100),
    IN p_first_name VARCHAR(50),
    IN p_last_name VARCHAR(50),
    IN p_birthdate DATE,
    OUT p_user_id INT
)
BEGIN
    INSERT INTO users (username, password, email, first_name, last_name, birthdate)
    VALUES (p_username, p_password, p_email, p_first_name, p_last_name, p_birthdate);
    
    SET p_user_id = LAST_INSERT_ID();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure add_event
-- -----------------------------------------------------

DELIMITER $$
USE `calendar_app`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `add_event`(
    IN p_user_id INT,
    IN p_title VARCHAR(100),
    IN p_description VARCHAR(1000),
    IN p_start_date DATE,
    IN p_start_time TIME,
    IN p_end_date DATE,
    IN p_end_time TIME,
    IN p_location_id INT
    )
BEGIN
    DECLARE current_datetime DATETIME DEFAULT CURRENT_TIMESTAMP;

    INSERT INTO events (user_id, title, description, start_date, start_time, end_date, end_time,location_id, created_at)
    VALUES (p_user_id, p_title, p_description, p_start_date, p_start_time, p_end_date, p_end_time, p_location_id, current_datetime);

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure add_user
-- -----------------------------------------------------

DELIMITER $$
USE `calendar_app`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `add_user`(
    IN p_username VARCHAR(50),
    IN p_password VARCHAR(100),
    IN p_email VARCHAR(100),
    IN p_first_name VARCHAR(50),
    IN p_last_name VARCHAR(50),
    IN p_birthdate DATE,
    OUT p_user_id INT
)
BEGIN
    INSERT INTO users (username, password, email, first_name, last_name, birthdate)
    VALUES (p_username, p_password, p_email, p_first_name, p_last_name, p_birthdate);
    
    SET p_user_id = LAST_INSERT_ID();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure check_email_unique
-- -----------------------------------------------------

DELIMITER $$
USE `calendar_app`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `check_email_unique`(
    IN p_email VARCHAR(100),
    OUT p_is_unique INT
)
BEGIN
    DECLARE count_email INT;

    SELECT COUNT(*)
    INTO count_email
    FROM users
    WHERE email = p_email;

    IF count_email > 0 THEN
        SET p_is_unique = 0; -- emailul nu este unic
    ELSE
        SET p_is_unique = 1; -- emailul este unic
    END IF;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure check_username_unique
-- -----------------------------------------------------

DELIMITER $$
USE `calendar_app`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `check_username_unique`(
    IN p_username VARCHAR(50),
    OUT p_is_unique INT
)
BEGIN
    DECLARE count_username INT;

    SELECT COUNT(*)
    INTO count_username
    FROM users
    WHERE username = p_username;

    IF count_username > 0 THEN
        SET p_is_unique = 0; -- username nu este unic
    ELSE
        SET p_is_unique = 1; -- username este unic
    END IF;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure delete_event_and_location
-- -----------------------------------------------------

DELIMITER $$
USE `calendar_app`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `delete_event_and_location`(
    IN p_event_id INT
)
BEGIN
    DECLARE v_location_id INT;

    -- Obținem location_id-ul evenimentului
    SELECT location_id INTO v_location_id
    FROM events
    WHERE event_id = p_event_id;

    -- Începem tranzacția
    START TRANSACTION;

    -- Ștergem evenimentul
    DELETE FROM events
    WHERE event_id = p_event_id;

    -- Ștergem locația asociată evenimentului
    DELETE FROM locations
    WHERE location_id = v_location_id;

    -- Confirmăm tranzacția
    COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure delete_user_by_username
-- -----------------------------------------------------

DELIMITER $$
USE `calendar_app`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `delete_user_by_username`(
    IN p_username VARCHAR(50)
)
BEGIN
    DECLARE v_user_id INT;
    
    -- Obținem user_id-ul folosind p_username
    SELECT user_id INTO v_user_id
    FROM users
    WHERE username = p_username;
    
    -- Începem tranzacția
    START TRANSACTION;
    
    -- Ștergem evenimentele utilizatorului
    DELETE FROM events
    WHERE user_id = v_user_id;

    -- Ștergem locațiile asociate evenimentelor utilizatorului
    DELETE FROM locations
    WHERE location_id IN (
        SELECT location_id
        FROM events
        WHERE user_id = v_user_id
    );

    -- Ștergem utilizatorul
    DELETE FROM users
    WHERE user_id = v_user_id;

    -- Confirmăm tranzacția
    COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_id_by_username
-- -----------------------------------------------------

DELIMITER $$
USE `calendar_app`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `get_id_by_username`(
    IN p_username VARCHAR(50)
)
BEGIN
    DECLARE v_user_id INT;

    SELECT user_id INTO v_user_id
    FROM users
    WHERE username = p_username;

    SELECT v_user_id AS user_id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_user_by_username
-- -----------------------------------------------------

DELIMITER $$
USE `calendar_app`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `get_user_by_username`(IN p_username VARCHAR(50))
BEGIN
    SELECT * FROM users WHERE username = p_username;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure update_event
-- -----------------------------------------------------

DELIMITER $$
USE `calendar_app`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `update_event`(
    IN p_event_id INT,
    IN p_title VARCHAR(100),
    IN p_description VARCHAR(1000),
    IN p_start_date DATE,
    IN p_start_time TIME,
    IN p_end_date DATE,
    IN p_end_time TIME,
    IN p_location_id INT
)
BEGIN
    DECLARE current_datetime DATETIME DEFAULT CURRENT_TIMESTAMP;

    -- Începem tranzacția
    START TRANSACTION;

    -- Actualizăm evenimentul
    UPDATE events
    SET
        title = p_title,
        description = p_description,
        start_date = p_start_date,
        start_time = p_start_time,
        end_date = p_end_date,
        end_time = p_end_time,
        location_id = p_location_id,
        created_at = current_datetime
    WHERE event_id = p_event_id;

    -- Confirmăm tranzacția
    COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure update_user_by_username
-- -----------------------------------------------------

DELIMITER $$
USE `calendar_app`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `update_user_by_username`(
     p_username VARCHAR(50),
     p_password VARCHAR(100),
     p_email VARCHAR(100),
     p_first_name VARCHAR(50),
     p_last_name VARCHAR(50),
     p_birthdate DATE
)
BEGIN
    UPDATE users
    SET 
        password = p_password,
        email = p_email,
        first_name = p_first_name,
        last_name = p_last_name,
        birthdate = p_birthdate
    WHERE username = p_username;
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
