CREATE DATABASE  IF NOT EXISTS `automation` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `automation`;
-- MySQL dump 10.13  Distrib 5.7.25, for Win64 (x86_64)
--
-- Host: localhost    Database: automation
-- ------------------------------------------------------
-- Server version	5.7.25-log

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
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(25) NOT NULL,
  `name` varchar(75) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='list of applications to be tested';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (1,'COMMON','Common');
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `element`
--

DROP TABLE IF EXISTS `element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pageid` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `location` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pageid_elementid` (`pageid`,`name`),
  KEY `FK_page_element_idx` (`pageid`),
  CONSTRAINT `FK_page_element` FOREIGN KEY (`pageid`) REFERENCES `page` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='page elements';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `element`
--

LOCK TABLES `element` WRITE;
/*!40000 ALTER TABLE `element` DISABLE KEYS */;
INSERT INTO `element` VALUES (1,1,'username','username'),(2,1,'password','password'),(3,1,'organization','organization'),(4,1,'loginbutton','login'),(5,2,'logout','sign-out');
/*!40000 ALTER TABLE `element` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `page`
--

DROP TABLE IF EXISTS `page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appid` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `FK_application_page_idx` (`appid`),
  CONSTRAINT `FK_application_page` FOREIGN KEY (`appid`) REFERENCES `application` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `page`
--

LOCK TABLES `page` WRITE;
/*!40000 ALTER TABLE `page` DISABLE KEYS */;
INSERT INTO `page` VALUES (1,1,'LoginPage'),(2,1,'HomePage');
/*!40000 ALTER TABLE `page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stepid` int(11) NOT NULL,
  `name` varchar(75) CHARACTER SET latin1 NOT NULL,
  `parentid` int(11) DEFAULT NULL,
  `recorder` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_stepid_name` (`id`,`name`),
  KEY `FK_step_record_idx` (`stepid`),
  KEY `FK_record_record_idx` (`parentid`),
  CONSTRAINT `FK_record_record` FOREIGN KEY (`parentid`) REFERENCES `record` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_step_record` FOREIGN KEY (`stepid`) REFERENCES `step` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recordparams`
--

DROP TABLE IF EXISTS `recordparams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recordparams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idrecord` int(11) NOT NULL,
  `name` varchar(75) NOT NULL,
  `value` longtext NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1_record_recordparams_idx` (`idrecord`),
  CONSTRAINT `FK1_record_recordparams` FOREIGN KEY (`idrecord`) REFERENCES `record` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recordparams`
--

LOCK TABLES `recordparams` WRITE;
/*!40000 ALTER TABLE `recordparams` DISABLE KEYS */;
/*!40000 ALTER TABLE `recordparams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `step`
--

DROP TABLE IF EXISTS `step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `step` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `testcaseid` int(11) NOT NULL,
  `code` varchar(45) NOT NULL,
  `name` varchar(75) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `FK1_testcase_step_idx` (`testcaseid`),
  CONSTRAINT `FK1_testcase_step` FOREIGN KEY (`testcaseid`) REFERENCES `testcase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COMMENT='step with in a test case';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `step`
--

LOCK TABLES `step` WRITE;
/*!40000 ALTER TABLE `step` DISABLE KEYS */;
INSERT INTO `step` VALUES (2,2,'lgin','Test_1_LoginTestCase');
/*!40000 ALTER TABLE `step` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `steparams`
--

DROP TABLE IF EXISTS `steparams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `steparams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stepid` int(11) NOT NULL,
  `name` varchar(75) NOT NULL,
  `value` longtext NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_step_stepparams_idx` (`stepid`),
  CONSTRAINT `fk_step_stepparams` FOREIGN KEY (`stepid`) REFERENCES `step` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `steparams`
--

LOCK TABLES `steparams` WRITE;
/*!40000 ALTER TABLE `steparams` DISABLE KEYS */;
/*!40000 ALTER TABLE `steparams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stepexpectedresult`
--

DROP TABLE IF EXISTS `stepexpectedresult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stepexpectedresult` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stepid` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `name` varchar(75) DEFAULT NULL,
  `value` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `FK_step_stepexpectedresult_idx` (`stepid`),
  CONSTRAINT `FK_step_stepexpectedresult` FOREIGN KEY (`stepid`) REFERENCES `step` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='expected result to a step/test case';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stepexpectedresult`
--

LOCK TABLES `stepexpectedresult` WRITE;
/*!40000 ALTER TABLE `stepexpectedresult` DISABLE KEYS */;
/*!40000 ALTER TABLE `stepexpectedresult` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testcase`
--

DROP TABLE IF EXISTS `testcase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testcase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appid` int(11) NOT NULL,
  `code` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `FK1_application_testcase_idx` (`appid`),
  CONSTRAINT `FK1_application_testcase` FOREIGN KEY (`appid`) REFERENCES `application` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Test suits records';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testcase`
--

LOCK TABLES `testcase` WRITE;
/*!40000 ALTER TABLE `testcase` DISABLE KEYS */;
INSERT INTO `testcase` VALUES (2,1,'login','LoginTestCase');
/*!40000 ALTER TABLE `testcase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testcaseparams`
--

DROP TABLE IF EXISTS `testcaseparams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testcaseparams` (
  `id` int(11) NOT NULL,
  `testcaseid` int(11) NOT NULL,
  `name` varchar(75) NOT NULL,
  `value` longtext NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_testcase_testcaseparams_idx` (`testcaseid`),
  CONSTRAINT `FK_testcase_testcaseparams` FOREIGN KEY (`testcaseid`) REFERENCES `testcase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='test case parameters';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testcaseparams`
--

LOCK TABLES `testcaseparams` WRITE;
/*!40000 ALTER TABLE `testcaseparams` DISABLE KEYS */;
INSERT INTO `testcaseparams` VALUES (1,2,'username','joeuser'),(2,2,'password','joeuser'),(3,2,'organization','');
/*!40000 ALTER TABLE `testcaseparams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'automation'
--

--
-- Dumping routines for database 'automation'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-16 19:57:17
