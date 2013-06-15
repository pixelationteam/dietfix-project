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
-- Table structure for table `known_words`
--

DROP TABLE IF EXISTS `known_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `known_words` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(100) NOT NULL,
  `pos_tag` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pos_tag_id` (`pos_tag`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `known_words`
--

LOCK TABLES `known_words` WRITE;
/*!40000 ALTER TABLE `known_words` DISABLE KEYS */;
INSERT INTO `known_words` VALUES (28,'be','VB'),(29,'have','VB'),(30,'do','VB'),(31,'say','VB'),(32,'get','VB'),(33,'make','VB'),(34,'go','VB'),(35,'know','VB'),(36,'take','VB'),(37,'see','VB'),(38,'come','VB'),(39,'think','VB'),(40,'look','VB'),(41,'want','VB'),(42,'give','VB'),(43,'use','VB'),(44,'find','VB'),(45,'tell','VB'),(46,'ask','VB'),(47,'work','VB'),(48,'seem','VB'),(49,'feel','VB'),(50,'try','VB'),(51,'leave','VB'),(52,'call','VB'),(53,'lose','VB'),(55,'eat','VB');
/*!40000 ALTER TABLE `known_words` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-13 22:15:50
