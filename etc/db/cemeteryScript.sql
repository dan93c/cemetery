SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cemeterydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cemeterydb` DEFAULT CHARACTER SET utf8 ;
USE `cemeterydb` ;

-- -----------------------------------------------------
-- Table `cemeterydb`.`cemeteries`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cemeterydb`.`cemeteries` ;

CREATE TABLE IF NOT EXISTS `cemeterydb`.`cemeteries` (
  `code` VARCHAR(45) NOT NULL,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cemeterydb`.`plots`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cemeterydb`.`plots` ;

CREATE TABLE IF NOT EXISTS `cemeterydb`.`plots` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `surface` VARCHAR(45) NULL DEFAULT NULL,
  `cemetery_code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cemetery_code_idx` (`cemetery_code` ASC),
  CONSTRAINT `fk_cemetery_code`
    FOREIGN KEY (`cemetery_code`)
    REFERENCES `cemeterydb`.`cemeteries` (`code`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cemeterydb`.`graves`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cemeterydb`.`graves` ;

CREATE TABLE IF NOT EXISTS `cemeterydb`.`graves` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nr_grave` VARCHAR(45) NULL DEFAULT NULL,
  `observations` VARCHAR(45) NULL DEFAULT NULL,
  `grave_type` VARCHAR(45) NULL DEFAULT NULL,
  `photo_scanned` LONGBLOB NULL DEFAULT NULL,
  `plot_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_plot_grave_idx` (`plot_id` ASC),
  CONSTRAINT `fk_plot_grave`
    FOREIGN KEY (`plot_id`)
    REFERENCES `cemeterydb`.`plots` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cemeterydb`.`deads`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cemeterydb`.`deads` ;

CREATE TABLE IF NOT EXISTS `cemeterydb`.`deads` (
  `cnp` VARCHAR(15) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `surname` VARCHAR(45) NULL DEFAULT NULL,
  `religion` VARCHAR(45) NULL DEFAULT NULL,
  `grave_id` INT(11) NOT NULL,
  PRIMARY KEY (`cnp`),
  INDEX `fk_grave_deads_idx` (`grave_id` ASC),
  CONSTRAINT `fk_grave_deads`
    FOREIGN KEY (`grave_id`)
    REFERENCES `cemeterydb`.`graves` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cemeterydb`.`appointments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cemeterydb`.`appointments` ;

CREATE TABLE IF NOT EXISTS `cemeterydb`.`appointments` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `funeral_date` TIMESTAMP NULL DEFAULT NULL,
  `cnp_dead` VARCHAR(15) NULL DEFAULT NULL,
  `grave_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_dead_idx` (`cnp_dead` ASC),
  INDEX `fk_grave_app_idx` (`grave_id` ASC),
  CONSTRAINT `fk_dead`
    FOREIGN KEY (`cnp_dead`)
    REFERENCES `cemeterydb`.`deads` (`cnp`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grave_app`
    FOREIGN KEY (`grave_id`)
    REFERENCES `cemeterydb`.`graves` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cemeterydb`.`claims_book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cemeterydb`.`claims_book` ;

CREATE TABLE IF NOT EXISTS `cemeterydb`.`claims_book` (
 `id` INT(11) NOT NULL AUTO_INCREMENT,
  `claims` VARCHAR(1000) NOT NULL,
   PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cemeterydb`.`concession_contracts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cemeterydb`.`concession_contracts` ;

CREATE TABLE IF NOT EXISTS `cemeterydb`.`concession_contracts` (
  `current_nr` VARCHAR(45) NOT NULL,
  `receipt_number` VARCHAR(45) NOT NULL,
  `release_date` DATETIME NULL DEFAULT NULL,
  `cnp` VARCHAR(15) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `surname` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `grave_id` INT(11) NOT NULL,
  PRIMARY KEY (`current_nr`),
  INDEX `fk_grave_cc_idx` (`grave_id` ASC),
  CONSTRAINT `fk_grave_cc`
    FOREIGN KEY (`grave_id`)
    REFERENCES `cemeterydb`.`graves` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cemeterydb`.`deads_without_family`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cemeterydb`.`deads_without_family` ;

CREATE TABLE IF NOT EXISTS `cemeterydb`.`deads_without_family` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `funeral_certificate` VARCHAR(50) NULL DEFAULT NULL,
  `iml_request` VARCHAR(50) NULL DEFAULT NULL,
  `grave_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_grave_d_idx` (`grave_id` ASC),
  CONSTRAINT `fk_grave_d`
    FOREIGN KEY (`grave_id`)
    REFERENCES `cemeterydb`.`graves` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cemeterydb`.`grave_requests`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cemeterydb`.`grave_requests` ;

CREATE TABLE IF NOT EXISTS `cemeterydb`.`grave_requests` (
  `current_nr` VARCHAR(45) NOT NULL,
  `nr_infocet` VARCHAR(100) NULL DEFAULT NULL,
  `registration_date` TIMESTAMP NULL DEFAULT NULL,
  `solving_stage` VARCHAR(45) NULL DEFAULT 'Intern',
  PRIMARY KEY (`current_nr`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cemeterydb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cemeterydb`.`users` ;

CREATE TABLE IF NOT EXISTS `cemeterydb`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cemeterydb`.`history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cemeterydb`.`history` ;

CREATE TABLE IF NOT EXISTS `cemeterydb`.`history` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `action_name` VARCHAR(100) NOT NULL,
  `modification_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_object_code` VARCHAR(1000) NOT NULL,
  `details` VARCHAR(1000) NULL DEFAULT NULL,
  `username` VARCHAR(45) NOT NULL,
   PRIMARY KEY (`id`),
  INDEX `fk_username_idx` (`username` ASC),
  CONSTRAINT `fk_username`
    FOREIGN KEY (`username`)
    REFERENCES `cemeterydb`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
