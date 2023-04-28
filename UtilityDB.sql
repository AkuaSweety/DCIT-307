SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `UtilityDB` ;
USE `UtilityDB`;

-- -----------------------------------------------------
-- Table `UtilityDB`.`Transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`Transaction` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`Transaction` (
  `transactionId` INT ZEROFILL NOT NULL AUTO_INCREMENT ,
  `phoneNo` VARCHAR(20) NOT NULL ,
  `type` VARCHAR(45) NOT NULL ,
  `utilityAcc` VARCHAR(45) NOT NULL ,
  `date` DATETIME NOT NULL ,
  PRIMARY KEY (`transactionId`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UtilityDB`.`TeleUtility`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`TeleUtility` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`TeleUtility` (
  `teleUtId` INT NOT NULL AUTO_INCREMENT ,
  `teleAcc` VARCHAR(45) NOT NULL ,
  `amountOwe` VARCHAR(45) NOT NULL ,
  `amountPaid` VARCHAR(45) NOT NULL ,
  `ownUpdate` VARCHAR(45) NOT NULL ,
  `paidUpdate` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`teleUtId`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UtilityDB`.`TelephoneAcc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`TelephoneAcc` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`TelephoneAcc` (
  `teleId` INT ZEROFILL NOT NULL AUTO_INCREMENT ,
  `teleKey` VARCHAR(45) NOT NULL ,
  `phoneNo` VARCHAR(20) NOT NULL ,
  `teleAcc` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`teleId`) ,
  INDEX teleAcc (`teleAcc` ASC) ,
  CONSTRAINT `teleAcc`
    FOREIGN KEY (`teleAcc` )
    REFERENCES `UtilityDB`.`TeleUtility` (`teleAcc` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UtilityDB`.`WaterUtility`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`WaterUtility` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`WaterUtility` (
  `waterUtId` INT NOT NULL AUTO_INCREMENT ,
  `waterAcc` VARCHAR(45) NOT NULL ,
  `amountOwe` VARCHAR(45) NOT NULL ,
  `amountPaid` VARCHAR(45) NOT NULL ,
  `ownUpdate` VARCHAR(45) NOT NULL ,
  `paidUpdate` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`waterUtId`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UtilityDB`.`WaterAcc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`WaterAcc` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`WaterAcc` (
  `waterId` INT ZEROFILL NOT NULL AUTO_INCREMENT ,
  `waterKey` VARCHAR(45) NOT NULL ,
  `phoneNo` VARCHAR(20) NOT NULL ,
  `waterAcc` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`waterId`) ,
  INDEX waterAcc (`waterAcc` ASC) ,
  CONSTRAINT `waterAcc`
    FOREIGN KEY (`waterAcc` )
    REFERENCES `UtilityDB`.`WaterUtility` (`waterAcc` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UtilityDB`.`LightUtility`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`LightUtility` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`LightUtility` (
  `lightUtId` INT NOT NULL AUTO_INCREMENT ,
  `lightAcc` VARCHAR(45) NOT NULL ,
  `amountOwe` VARCHAR(45) NOT NULL ,
  `amountPaid` VARCHAR(45) NOT NULL ,
  `ownUpdate` VARCHAR(45) NOT NULL ,
  `paidUpdate` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`lightUtId`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UtilityDB`.`LightAcc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`LightAcc` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`LightAcc` (
  `lightId` INT ZEROFILL NOT NULL AUTO_INCREMENT ,
  `lightKey` VARCHAR(45) NOT NULL ,
  `phoneNo` VARCHAR(20) NOT NULL ,
  `lightAcc` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`lightId`) ,
  INDEX lightAcc (`lightAcc` ASC) ,
  CONSTRAINT `lightAcc`
    FOREIGN KEY (`lightAcc` )
    REFERENCES `UtilityDB`.`LightUtility` (`lightAcc` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UtilityDB`.`SecurityData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`SecurityData` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`SecurityData` (
  `securityId` INT NOT NULL AUTO_INCREMENT ,
  `phoneNo` VARCHAR(20) NOT NULL ,
  `maxAmount` VARCHAR(45) NOT NULL ,
  `teleKey` VARCHAR(45) NOT NULL ,
  `lightKey` VARCHAR(45) NOT NULL ,
  `waterKey` VARCHAR(45) NOT NULL ,
  `PIN` INT ZEROFILL NULL ,
  `confirmation` VARCHAR(45) NOT NULL ,
  `PINsent` VARCHAR(1) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`securityId`) ,
  INDEX phoneNo (`phoneNo` ASC) ,
  INDEX teleKey (`teleKey` ASC) ,
  INDEX waterKey (`waterKey` ASC) ,
  INDEX lightKey (`lightKey` ASC) ,
  CONSTRAINT `phoneNo`
    FOREIGN KEY (`phoneNo` )
    REFERENCES `UtilityDB`.`Transaction` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `teleKey`
    FOREIGN KEY (`teleKey` )
    REFERENCES `UtilityDB`.`TelephoneAcc` (`teleKey` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `waterKey`
    FOREIGN KEY (`waterKey` )
    REFERENCES `UtilityDB`.`WaterAcc` (`maxAmount` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `lightKey`
    FOREIGN KEY (`lightKey` )
    REFERENCES `UtilityDB`.`LightAcc` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UtilityDB`.`BioData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`BioData` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`BioData` (
  `idBioData` INT ZEROFILL NOT NULL AUTO_INCREMENT ,
  `surname` VARCHAR(45) NOT NULL ,
  `otherNames` VARCHAR(100) NOT NULL ,
  `bankAccount` VARCHAR(20) NOT NULL ,
  `phoneNo` VARCHAR(20) NOT NULL ,
  `dateReg` DATETIME NOT NULL ,
  PRIMARY KEY (`idBioData`) ,
  INDEX phoneNo (`phoneNo` ASC) ,
  CONSTRAINT `phoneNo`
    FOREIGN KEY (`phoneNo` )
    REFERENCES `UtilityDB`.`SecurityData` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UtilityDB`.`OweData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`OweData` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`OweData` (
  `oweId` INT NOT NULL AUTO_INCREMENT ,
  `accNumber` VARCHAR(45) NOT NULL ,
  `dateUpdate` DATETIME NOT NULL DEFAULT '0' ,
  `previousReading` VARCHAR(45) NOT NULL DEFAULT '0' ,
  `newReading` VARCHAR(45) NOT NULL DEFAULT '0' ,
  `unitCharge` VARCHAR(45) NOT NULL DEFAULT '0' ,
  `otherCharges` VARCHAR(45) NOT NULL DEFAULT '0' ,
  `arears` VARCHAR(45) NOT NULL DEFAULT '0' ,
  `amtPaid` VARCHAR(45) NOT NULL DEFAULT '0' ,
  `datePaid` VARCHAR(45) NULL DEFAULT 'null' ,
  `totalCharge` VARCHAR(45) NOT NULL ,
  `month` VARCHAR(10) NOT NULL ,
  `year` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`oweId`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UtilityDB`.`OweAcc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`OweAcc` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`OweAcc` (
  `AccId` INT NOT NULL AUTO_INCREMENT ,
  `accNumber` VARCHAR(45) NOT NULL ,
  `surname` VARCHAR(45) NOT NULL ,
  `otherName` VARCHAR(45) NOT NULL ,
  `meterNo` VARCHAR(45) NOT NULL ,
  `Region` VARCHAR(45) NOT NULL ,
  `District` VARCHAR(45) NOT NULL ,
  `Zone` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`AccId`) ,
  INDEX accNumber (`accNumber` ASC) ,
  CONSTRAINT `accNumber`
    FOREIGN KEY (`accNumber` )
    REFERENCES `UtilityDB`.`OweData` (`accNumber` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Table `UtilityDB`.`SetUp`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`SetUp` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`SetUp` (
  `setUpId` INT NOT NULL AUTO_INCREMENT ,
  `setUpType` VARCHAR(45) NOT NULL ,
  `setUpValue` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`setUpId`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UtilityDB`.`AdminAcc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`AdminAcc` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`AdminAcc` (
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NULL ,
  `status` VARCHAR(45) NULL ,
  `Privilege` INT UNSIGNED NULL ,
  PRIMARY KEY (`username`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UtilityDB`.`AdminDetails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`AdminDetails` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`AdminDetails` (
  `idAdminDetails` INT NOT NULL AUTO_INCREMENT ,
  `Surname` VARCHAR(45) NULL ,
  `OtherNames` VARCHAR(45) NULL ,
  `Contact` VARCHAR(45) NULL ,
  `Address` VARCHAR(100) NULL ,
  `Relative` VARCHAR(50) NULL ,
  `RelativeContact` VARCHAR(45) NULL ,
  `CompanyName` VARCHAR(50) NULL, 
  `username` VARCHAR(45) NULL ,
  PRIMARY KEY (`idAdminDetails`) ,
  INDEX index1 () ,
  INDEX username (`username` ASC) ,
  CONSTRAINT `username`
    FOREIGN KEY (`username` )
    REFERENCES `UtilityDB`.`AdminAcc` (`username` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UtilityDB`.`FromPhone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`FromPhone` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`FromPhone` (
  `msgID` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `senderNumber` VARCHAR(45) NULL ,
  `message` VARCHAR(160) NULL ,
  `status` INT UNSIGNED NULL ,
  PRIMARY KEY (`msgID`) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `UtilityDB`.`ToPhone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UtilityDB`.`ToPhone` ;

CREATE  TABLE IF NOT EXISTS `UtilityDB`.`ToPhone` (
  `msgID` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `senderNumber` VARCHAR(45) NULL ,
  `message` VARCHAR(160) NULL ,
  `status` INT UNSIGNED NULL ,
  PRIMARY KEY (`msgID`) )
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
