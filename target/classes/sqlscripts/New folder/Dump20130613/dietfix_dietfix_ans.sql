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
-- Table structure for table `dietfix_ans`
--

DROP TABLE IF EXISTS `dietfix_ans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dietfix_ans` (
  `AnsID` int(11) NOT NULL AUTO_INCREMENT,
  `AnsObj` blob NOT NULL,
  `AnsExpert` enum('DIETITIAN','FITNESS_INSTRUCTOR','NUTRITIONIST') NOT NULL,
  `AnsDesc` varchar(255) NOT NULL,
  PRIMARY KEY (`AnsID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dietfix_ans`
--

LOCK TABLES `dietfix_ans` WRITE;
/*!40000 ALTER TABLE `dietfix_ans` DISABLE KEYS */;
INSERT INTO `dietfix_ans` VALUES (16,'¬í\0sr\0#pup.thesis.knowledgebase.AnswerData\0\0\0\0\0\0\0\0[\0dependenciest\0[Lpup/thesis/nlu/pos/TypedDep;L\0mappingst\0Ljava/util/HashMap;xpur\0[Lpup.thesis.nlu.pos.TypedDep;c$¸¤w ü\0\0xp\0\0\0sr\0pup.thesis.nlu.pos.TypedDepzÐêiò\r\0[\0actionst\0[Ljava/lang/String;L\0dept\0Lpup/thesis/nlu/pos/Word;L\0expt\0Ljava/lang/String;L\0govq\0~\0L\0relnq\0~\0	xppsr\0pup.thesis.nlu.pos.Word\0\0\0\0\0\0\0\0I\0indexL\0lemmaq\0~\0	L\0tagq\0~\0	xp\0\0\0t\0Yout\0PRPpsq\0~\0\0\0\0t\0killt\0VBPt\0nsubjsq\0~\0psq\0~\0\0\0\0q\0~\0\rq\0~\0psq\0~\0\0\0\0t\0eatt\0VBPq\0~\0sq\0~\0psq\0~\0\0\0\0q\0~\0q\0~\0psq\0~\0\0\0\0\0t\0ROOTpt\0rootsq\0~\0psq\0~\0\0\0\0t\0thet\0DTpsq\0~\0\0\0\0t\0catt\0NNt\0detsq\0~\0psq\0~\0\0\0\0q\0~\0\"q\0~\0#psq\0~\0\0\0\0q\0~\0q\0~\0t\0dobjsq\0~\0psq\0~\0\0\0\0q\0~\0q\0~\0psq\0~\0\0\0\0q\0~\0q\0~\0t\0conj_orsq\0~\0psq\0~\0\0\0\0t\0thet\0DTpsq\0~\0\0\0\0t\0mouset\0NNq\0~\0$sq\0~\0psq\0~\0\0\0\0q\0~\02q\0~\03psq\0~\0\0\0\0q\0~\0q\0~\0q\0~\0(sr\0java.util.HashMapÚÁÃ`Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0\0x','DIETITIAN','(1:[TEST2]),(1:[TEST])');
/*!40000 ALTER TABLE `dietfix_ans` ENABLE KEYS */;
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
