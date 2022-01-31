-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: congratulator
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.34-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (9,'Друзья'),(2,'Знакомые'),(3,'Сотрудники');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Идер',
  `first_name` varchar(255) NOT NULL COMMENT 'Имя',
  `last_name` varchar(255) NOT NULL COMMENT 'Фамилия',
  `middle_name` varchar(255) NOT NULL COMMENT 'Отчество',
  `birthday` datetime NOT NULL COMMENT 'День рождения',
  `category_id` int(10) unsigned DEFAULT NULL COMMENT 'Идер категории',
  `photo` varchar(255) DEFAULT '' COMMENT 'Фотография',
  PRIMARY KEY (`id`),
  KEY `first_name` (`first_name`),
  KEY `birthday` (`birthday`),
  KEY `fk_category` (`category_id`),
  KEY `last_name` (`last_name`) USING BTREE,
  KEY `middle_name` (`middle_name`) USING BTREE,
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'Петров','Петр','Петрович','1900-11-11 00:00:00',NULL,NULL),(4,'Иванов','Фамилия 5','Иванович','1901-10-10 00:00:00',2,NULL),(12,'Имя 2','Фамилия 2','Отчество 3','1993-01-01 00:00:00',2,NULL),(14,'Имя 1','фамилия 1','Отчество 1','1990-04-04 00:00:00',3,''),(15,'Имя 2','фамилия 2','Отчество 2','1990-06-06 00:00:00',NULL,''),(16,'Имя 3','фамилия 3','Отчество 3','1990-07-08 00:00:00',NULL,''),(17,'Имя 4','фамилия 4','Отчество 4','1990-08-01 00:00:00',NULL,''),(18,'Имя 1','Afv 1','Отч 1','2022-01-02 21:00:00',NULL,NULL),(19,'йцукйцу','йцукйц','йцукйцу','2022-01-30 21:00:00',9,NULL),(20,'3у123у','12у2','123у213','2022-01-30 21:00:00',3,NULL),(21,'3у123у','qwe','wqefw','2022-01-30 21:00:00',NULL,NULL),(22,'Имя 6','Фамилия 6','Отчество 6','2022-05-19 21:00:00',3,NULL);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-31 22:29:04
