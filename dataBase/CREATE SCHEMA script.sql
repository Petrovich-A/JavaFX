-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema employeesort
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema employeesort
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `employeesort` DEFAULT CHARACTER SET utf8 ;
USE `employeesort` ;

-- -----------------------------------------------------
-- Table `employeesort`.`employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeesort`.`employees` (
  `id_employee` INT NOT NULL AUTO_INCREMENT,
  `personnel_number` INT(5) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_employee`),
  UNIQUE INDEX `idEmployees_UNIQUE` (`id_employee` ASC),
  UNIQUE INDEX `personnel_number_UNIQUE` (`personnel_number` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb3;
 
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

insert into employees (personnel_number,name,surname) values (42901, 'Анастасия', 'Смирнова');
insert into employees (personnel_number,name,surname) values (42902, 'Анжелика', 'Иванова');
insert into employees (personnel_number,name,surname) values (42903, 'Анжелика', 'Яковлева');
insert into employees (personnel_number,name,surname) values (42904, 'Анна', 'Кузнецова');
insert into employees (personnel_number,name,surname) values (42905, 'Анна', 'Герасимова');
insert into employees (personnel_number,name,surname) values (42906, 'Светлана', 'Кузнецова');
insert into employees (personnel_number,name,surname) values (42907, 'Валентина', 'Соколова');
insert into employees (personnel_number,name,surname) values (42908, 'Валерия', 'Попова');
insert into employees (personnel_number,name,surname) values (42909, 'Вера', 'Лебедева');
insert into employees (personnel_number,name,surname) values (42910, 'Вероника', 'Новикова');
insert into employees (personnel_number,name,surname) values (42911, 'Александр', 'Петров');
insert into employees (personnel_number,name,surname) values (42912, 'Роман', 'Петров');
insert into employees (personnel_number,name,surname) values (42913, 'Алексей', 'Волков');
insert into employees (personnel_number,name,surname) values (42914, 'Андрей', 'Соловьёв');
insert into employees (personnel_number,name,surname) values (42915, 'Антон', 'Васильев');
insert into employees (personnel_number,name,surname) values (42916, 'Вадим', 'Зайцев');
insert into employees (personnel_number,name,surname) values (42917, 'Валерий', 'Павлов');
insert into employees (personnel_number,name,surname) values (42918, 'Валерий', 'Павлов');
insert into employees (personnel_number,name,surname) values (42919, 'Владимир', 'Тарасов');
insert into employees (personnel_number,name,surname) values (42920, 'Владимир', 'Белов');
insert into employees (personnel_number,name,surname) values (42921, 'Владимир', 'Большаков');