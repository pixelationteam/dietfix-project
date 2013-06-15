CREATE DATABASE  IF NOT EXISTS `dietfix` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dietfix`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: dietfix
-- ------------------------------------------------------
-- Server version	5.5.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(125) NOT NULL,
  `salt` varchar(125) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `date_created` date NOT NULL,
  `date_modified` date NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(2,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(3,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(4,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(5,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(6,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(7,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(8,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(9,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(10,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(11,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(12,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(13,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(14,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(15,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(16,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(17,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10'),(18,'paulzed','kakashi17','12345678','paul','v','artigo','2013-06-10','2013-06-10');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-13 22:15:51
