use `necklace`;

CREATE DATABASE `necklace` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
SELECT * FROM necklace.stones;

CREATE TABLE `stones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `cost` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `necklace` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;



INSERT INTO `necklace`.`stones`
(`id`,`name`,`type`,`weight`,`cost`)
VALUES (1,'almaz','PRECISIOUS',55,7);

INSERT INTO `necklace`.`stones`
(`id`,`name`,`type`,`weight`,`cost`)
VALUES (2,'rubin','PRECISIOUS',66,10);

INSERT INTO `necklace`.`stones`
(`id`,`name`,`type`,`weight`,`cost`)
VALUES (3,'ametit','SEMIPRECISIOUS',77,5);
