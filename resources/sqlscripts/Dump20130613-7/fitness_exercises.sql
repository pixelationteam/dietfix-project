CREATE DATABASE  IF NOT EXISTS `fitness` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `fitness`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: fitness
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
-- Table structure for table `exercises`
--

DROP TABLE IF EXISTS `exercises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exercises` (
  `Exercise_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Exercise_Desc` varchar(255) DEFAULT NULL,
  `Exercise_Type_ID` int(11) DEFAULT NULL,
  `Calorie_Burned` double DEFAULT NULL,
  PRIMARY KEY (`Exercise_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercises`
--

LOCK TABLES `exercises` WRITE;
/*!40000 ALTER TABLE `exercises` DISABLE KEYS */;
INSERT INTO `exercises` VALUES (1,'bicycling,stationary,100 watts,light effort',1,10),(2,'bicycling,stationary,150 watts,moderate effort',1,12),(3,'bicycling,stationary,200 watts,vigorous effort',1,18),(4,'bicycling,stationary,250 watts,very vigorous effort',1,22),(5,'bicycling,stationary,50 watts,veryl light effort',1,5),(6,'bicycling,stationary,general',1,12),(7,'calisthenics,pushups,situps,pullups,jumping jacks,heavy,vigorous effort',1,14),(8,'calisthenics,home exercise,light effort,moderate effort,general,back exercise,going up and down from floor',1,6),(9,'circuit training,including some aerobic movement with minimal rest,general',1,14),(10,'health club exercise,general',1,10),(11,'mild stretching',1,4),(12,'rowing,stationary ergometer,general',1,12),(13,'rowing,stationary,100 watts,moderate effort',1,12),(14,'rowing,stationary,150 watts,vigorous effort',1,15),(15,'rowing,stationary,200 watts,very vigorous effort',1,21),(16,'rowing,stationary,50 watts,light effort',1,6),(17,'ski machine,general',1,12),(18,'slimnastics,jazzercise',1,11),(19,'stair-treadmill ergometer,general',1,16),(20,'stretching,hatha yoga',1,4),(21,'teaching aerobic exercise class',1,11),(22,'water aerobics,water calisthenics',1,7),(23,'weight lifting,light effort,moderate effort,light workout,general',1,5),(24,'weight lifting,power lifting,body building,vigorous effort',1,11),(25,'whirlpool,sitting',1,2),(26,'aerobic,high impact',2,12),(27,'aerobic,low impact',2,9),(28,'running,10.9 mph,5.5 min/mile',3,32),(29,'running,10 mph,6 min/mile',3,28),(30,'running,9 mph,6.5 min/mile',3,26),(31,'running,8.6 mph,7 min/mile',3,25),(32,'running,8 mph,7.5 min/mile',3,24),(33,'running,7.5 mph,8 min/mile',3,22),(34,'running,7 mph,8.5 min/mile',3,20),(35,'running,6.7 mph,9 min/mile',3,19),(36,'running,6 mph,10 min/mile',3,18),(37,'running,5.2 mph,11.5 min/mile',3,16),(38,'running,5 mph,12 min/mile',3,14),(39,'jog and walk combination',3,11),(40,'walking,5 mph',4,14),(41,'walking,4.5 mph,level,firm surface,very very brisk',4,11),(42,'walking,4 mph,level,firm surface,very brisk pace',4,9),(43,'walking,3.5 mph,uphill',4,11),(44,'walking,3 mph,level,moderate pace,firm surface',4,6),(45,'walking,2.5 mph,firm surface',4,5),(46,'swimming laps,freestyle,fast,vigorous effort',5,18),(47,'swimming laps,freestyle,slow,moderate effort',5,12),(48,'swimming,backstroke,general',5,12),(49,'swimming,breaststroke,general',5,18),(50,'swimming,butterfly,general',5,19);
/*!40000 ALTER TABLE `exercises` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-13 23:12:10
