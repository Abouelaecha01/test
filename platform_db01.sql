-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: platform_db01
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.28-MariaDB

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
-- Table structure for table `cv_certificate`
--

DROP TABLE IF EXISTS `cv_certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cv_certificate` (
  `cv_certificateid` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `date_acquisition` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `certificate_id` bigint(20) DEFAULT NULL,
  `cvid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cv_certificateid`),
  KEY `FK72km6ytocujyk800o18pnrcbm` (`certificate_id`),
  KEY `FKejpftmdo639o491vfb375gpw6` (`cvid`),
  CONSTRAINT `FK72km6ytocujyk800o18pnrcbm` FOREIGN KEY (`certificate_id`) REFERENCES `ref_certificate` (`certificateid`),
  CONSTRAINT `FKejpftmdo639o491vfb375gpw6` FOREIGN KEY (`cvid`) REFERENCES `cv_info` (`cvid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cv_certificate`
--

LOCK TABLES `cv_certificate` WRITE;
/*!40000 ALTER TABLE `cv_certificate` DISABLE KEYS */;
INSERT INTO `cv_certificate` VALUES (1,'2023-11-22 14:34:48.000000','2022-06-15 01:00:00.000000','2023-11-22 14:34:48.000000',2,1);
/*!40000 ALTER TABLE `cv_certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cv_experience`
--

DROP TABLE IF EXISTS `cv_experience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cv_experience` (
  `cv_experienceid` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `date_debut` datetime(6) DEFAULT NULL,
  `date_fin` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `cvid` bigint(20) DEFAULT NULL,
  `fonction` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cv_experienceid`),
  KEY `FK17dy71crnncadcm5e75sd2e7o` (`company_id`),
  KEY `FK6vmb3kc4rlj41fg7kl4cvr66o` (`cvid`),
  CONSTRAINT `FK17dy71crnncadcm5e75sd2e7o` FOREIGN KEY (`company_id`) REFERENCES `ref_company` (`companyid`),
  CONSTRAINT `FK6vmb3kc4rlj41fg7kl4cvr66o` FOREIGN KEY (`cvid`) REFERENCES `cv_info` (`cvid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cv_experience`
--

LOCK TABLES `cv_experience` WRITE;
/*!40000 ALTER TABLE `cv_experience` DISABLE KEYS */;
INSERT INTO `cv_experience` VALUES (1,'2023-11-22 14:33:33.000000','2023-01-01 01:00:00.000000','2023-12-31 01:00:00.000000','Worked on project XYZ','2023-11-22 14:33:33.000000',1,1,NULL);
/*!40000 ALTER TABLE `cv_experience` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cv_formation`
--

DROP TABLE IF EXISTS `cv_formation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cv_formation` (
  `cv_formationid` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `date_debut` datetime(6) DEFAULT NULL,
  `date_fin` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `cvid` bigint(20) DEFAULT NULL,
  `niveau_formation_id` bigint(20) DEFAULT NULL,
  `mention_id` bigint(20) DEFAULT NULL,
  `ecole_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cv_formationid`),
  KEY `FKonsel0lcn1ji14ck2jn2cqac0` (`country_id`),
  KEY `FK80av386djs70ftlr28m79o8s6` (`cvid`),
  KEY `FK2xt3fy5lnfw9eeqayeqp68mot` (`niveau_formation_id`),
  KEY `FKp6vdo650f8ncnqwg8e2kkv7qm` (`mention_id`),
  KEY `FK9m4llyrfqh08hjst47mdchdub` (`ecole_id`),
  CONSTRAINT `FK2xt3fy5lnfw9eeqayeqp68mot` FOREIGN KEY (`niveau_formation_id`) REFERENCES `ref_level_formation` (`niveau_formationid`),
  CONSTRAINT `FK80av386djs70ftlr28m79o8s6` FOREIGN KEY (`cvid`) REFERENCES `cv_info` (`cvid`),
  CONSTRAINT `FK9m4llyrfqh08hjst47mdchdub` FOREIGN KEY (`ecole_id`) REFERENCES `ref_school` (`ecoleid`),
  CONSTRAINT `FKonsel0lcn1ji14ck2jn2cqac0` FOREIGN KEY (`country_id`) REFERENCES `ref_country` (`countryid`),
  CONSTRAINT `FKp6vdo650f8ncnqwg8e2kkv7qm` FOREIGN KEY (`mention_id`) REFERENCES `ref_mention` (`mentionid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cv_formation`
--

LOCK TABLES `cv_formation` WRITE;
/*!40000 ALTER TABLE `cv_formation` DISABLE KEYS */;
INSERT INTO `cv_formation` VALUES (1,'2023-11-22 14:34:03.000000','2023-01-01 01:00:00.000000','2023-12-31 01:00:00.000000','Bachelor in Computer Science','2023-11-22 14:34:03.000000',2,1,3,2,1);
/*!40000 ALTER TABLE `cv_formation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cv_info`
--

DROP TABLE IF EXISTS `cv_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cv_info` (
  `cvid` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `cv_ref` varchar(255) DEFAULT NULL,
  `date_de_naissance` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fixmobile` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `tel1` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cvid`),
  KEY `FKktxqk8k7m4x0qhgmtkev77pou` (`user_id`),
  CONSTRAINT `FKktxqk8k7m4x0qhgmtkev77pou` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cv_info`
--

LOCK TABLES `cv_info` WRITE;
/*!40000 ALTER TABLE `cv_info` DISABLE KEYS */;
INSERT INTO `cv_info` VALUES (1,'2023-11-21 18:02:25.000000','YWJvdWVsYWVjaGEuMTJoQGdtYWlsLmNvbS01OGExNWVjNi1mYWVmLTRkZTktODYxMy03NzZmMTA1NWM5YTE=','2001-06-09 00:00:00.000000','abouelaecha.12h@gmail.com','0522458756','ighir','mouhcine','0625254125','2023-11-21 18:02:25.000000',NULL,NULL),(2,'2023-11-24 04:22:51.000000','YWJvdWVsYWVjaGEuMTdoQGdtYWlsLmNvbS1jYTc0ZWY3Mi04MGFjLTRlNDAtYWNjMC1kZTFlZTAyM2E3ODE=','2001-06-09 00:00:00.000000','abouelaecha.17h@gmail.com','0522458756','ighir','mouhcine','0625254125','2023-11-24 04:22:51.000000',NULL,NULL),(3,'2023-11-24 04:23:28.000000','YWJvdWVsYWVjaGEuMTdoQGdtYWlsLmNvbS05Y2U5ODhjNS04N2Q5LTQyZmMtOTQ1Ny03MTM0NjgyMzc4NDY=','2001-06-09 00:00:00.000000','abouelaecha.17h@gmail.com','0522458756','ighir','mouhcine','0625254125','2023-11-24 04:23:28.000000',NULL,NULL),(4,'2023-11-24 05:09:39.000000','YWJvdWVsYWVjaGEuMTRoaEBnbWFpbC5jb20tN2E0MDlhZWYtNDc4MS00YWIxLTg4MTYtMWVlYzFmMGM4OGU4','2001-06-09 00:00:00.000000','abouelaecha.14hh@gmail.com','0522458756','ighir','mouhcine','0625254125','2023-11-24 05:09:39.000000',NULL,NULL),(5,'2023-11-24 18:36:43.000000','YWJvdWVsYWVjaGEuMTloQGdtYWlsLmNvbS04YmI4NjA5OS00N2Q0LTRjN2YtYjI0Zi0xNjIyNmE2ZWI4MGY=','2001-06-09 00:00:00.000000','abouelaecha.19h@gmail.com','0522458756','ighir','mouhcine','0625254125','2023-11-24 18:36:43.000000',NULL,NULL),(6,'2023-11-24 18:46:25.000000','YWJvdWVsYWVjaGEuOTloQGdtYWlsLmNvbS00NmY3ZjFjMy1iNzQ3LTRiNDYtOGNlMy05MGFkYzljNzFjNGU=','2001-06-09 00:00:00.000000','abouelaecha.99h@gmail.com','0522458756','ighir','mouhcine','0625254125','2023-11-24 18:46:25.000000',NULL,NULL),(7,'2023-11-25 00:39:21.000000','YWJvdWVsYWVjaGEuODhoQGdtYWlsLmNvbS05ZDQwYjIxZS0wODFlLTQ2NDUtOTU1ZS1kZDE1MzQ3OWMzYTQ=','2001-06-09 00:00:00.000000','abouelaecha.88h@gmail.com','0522458756','ighir','mouhcine','0625254125','2023-11-25 00:39:21.000000',NULL,NULL),(8,'2023-11-26 17:26:07.000000','YWJvdWVsYWVjaGEuMDhoQGdtYWlsLmNvbS1lNTc2ZDg0Zi0xMDE2LTRiMjgtODBjZi03OGQwZTZjMjk2Mzc=','2001-06-09 00:00:00.000000','abouelaecha.08h@gmail.com','0522458756','ighir','mouhcine','0625254125','2023-11-26 17:26:07.000000',NULL,NULL),(9,'2023-11-26 17:26:48.000000','YWJvdWVsYWVjaGEuMDg4aEBnbWFpbC5jb20tNzIwOTUzZTUtZDgwNC00Njg2LWE5MzUtZWYwOTJiMTA5NTZi','2001-06-09 00:00:00.000000','abouelaecha.088h@gmail.com','0522458756','ighir','mouhcine','0625254125','2023-11-26 17:26:48.000000',NULL,NULL),(10,'2023-11-27 01:54:49.000000','YWJvdWVsYWVjaGEuMDA4aEBnbWFpbC5jb20tNTBmOGZiMjktNWJiYi00Y2MyLWEwZmMtMmVhMzU0YjAzNDc2','2001-06-09 00:00:00.000000','abouelaecha.008h@gmail.com','0522458756','ighir','mouhcine','0625254125','2023-11-27 01:54:49.000000',NULL,NULL),(11,'2023-11-28 14:47:31.000000','YWJvdWVsYWVjaGEuNzhoQGdtYWlsLmNvbS0zMWFhMmQxYy02YmNiLTRlMjctYmI4Mi1jZGI0YWFhODJlYWI=','2001-06-09 00:00:00.000000','abouelaecha.78h@gmail.com','0522458756','ighir','mouhcine','0625254125','2023-11-28 14:47:31.000000',NULL,'234 Elm Street Suite 567 Anytown, NY 12345 USA');
/*!40000 ALTER TABLE `cv_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cv_language`
--

DROP TABLE IF EXISTS `cv_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cv_language` (
  `cv_languagesid` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `cvid` bigint(20) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `niveaux_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cv_languagesid`),
  KEY `FKro2v1enu2i6tkj89drs27rskl` (`cvid`),
  KEY `FKhvnrjro8f14xs0aj0otr91ujt` (`language_id`),
  KEY `FK2du5x5u1un3h5lojj87xg14dd` (`niveaux_id`),
  CONSTRAINT `FK2du5x5u1un3h5lojj87xg14dd` FOREIGN KEY (`niveaux_id`) REFERENCES `ref_level_language` (`niveau_languageid`),
  CONSTRAINT `FKhvnrjro8f14xs0aj0otr91ujt` FOREIGN KEY (`language_id`) REFERENCES `ref_language` (`languageid`),
  CONSTRAINT `FKro2v1enu2i6tkj89drs27rskl` FOREIGN KEY (`cvid`) REFERENCES `cv_info` (`cvid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cv_language`
--

LOCK TABLES `cv_language` WRITE;
/*!40000 ALTER TABLE `cv_language` DISABLE KEYS */;
INSERT INTO `cv_language` VALUES (1,'2023-11-22 14:32:44.000000','2023-11-22 14:32:44.000000',1,1,3);
/*!40000 ALTER TABLE `cv_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cv_skill`
--

DROP TABLE IF EXISTS `cv_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cv_skill` (
  `cv_skillid` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `cvid` bigint(20) DEFAULT NULL,
  `niveau_skill_id` bigint(20) DEFAULT NULL,
  `skill_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cv_skillid`),
  KEY `FK289rvommym5pl4wjjdpbg2c85` (`cvid`),
  KEY `FKrww2iebgo68k6txav1jqogqtt` (`niveau_skill_id`),
  KEY `FK3wrs9w8aoyqhxet41om9nstbi` (`skill_id`),
  CONSTRAINT `FK289rvommym5pl4wjjdpbg2c85` FOREIGN KEY (`cvid`) REFERENCES `cv_info` (`cvid`),
  CONSTRAINT `FK3wrs9w8aoyqhxet41om9nstbi` FOREIGN KEY (`skill_id`) REFERENCES `ref_skill` (`skillid`),
  CONSTRAINT `FKrww2iebgo68k6txav1jqogqtt` FOREIGN KEY (`niveau_skill_id`) REFERENCES `ref_level_skill` (`niveau_skillid`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cv_skill`
--

LOCK TABLES `cv_skill` WRITE;
/*!40000 ALTER TABLE `cv_skill` DISABLE KEYS */;
INSERT INTO `cv_skill` VALUES (5,'2023-11-23 22:26:10.000000','2023-11-23 22:26:10.000000',1,1,2),(6,'2023-11-23 22:56:02.000000','2023-11-23 22:56:02.000000',1,1,2),(7,'2023-11-24 21:14:59.000000','2023-11-24 21:14:59.000000',1,1,2),(8,'2023-11-24 21:15:51.000000','2023-11-24 21:15:51.000000',1,1,3),(9,'2023-11-25 03:34:47.000000','2023-11-25 03:34:47.000000',1,2,3),(10,'2023-11-25 03:48:06.000000','2023-11-25 03:48:06.000000',1,2,2),(11,'2023-11-25 04:17:07.000000','2023-11-25 04:17:07.000000',1,2,2),(13,'2023-11-25 04:46:33.000000','2023-11-25 04:46:33.000000',1,2,3),(16,'2023-11-25 20:42:31.000000','2023-11-25 20:42:31.000000',1,1,2),(17,'2023-11-25 23:59:08.000000','2023-11-25 23:59:08.000000',1,1,2),(19,'2023-11-26 18:27:48.000000','2023-11-26 18:27:48.000000',1,1,1),(20,'2023-11-26 18:45:00.000000','2023-11-26 18:45:00.000000',1,1,1),(21,'2023-11-26 23:14:24.000000','2023-11-26 23:14:24.000000',1,1,2),(22,'2023-11-27 01:58:12.000000','2023-11-27 01:58:12.000000',1,1,2),(23,'2023-11-27 01:59:36.000000','2023-11-27 01:59:36.000000',1,1,2),(24,'2023-11-27 02:24:49.000000','2023-11-27 02:24:49.000000',1,1,2),(27,'2023-11-27 14:07:02.000000','2023-11-27 14:07:02.000000',1,2,2),(28,'2023-11-27 14:24:54.000000','2023-11-27 14:24:54.000000',1,2,1),(36,'2023-12-01 02:51:45.000000','2023-12-01 02:51:45.000000',11,1,1),(39,'2023-12-01 04:20:28.000000','2023-12-01 04:20:28.000000',11,1,1),(40,'2023-12-01 16:17:13.000000','2023-12-01 16:17:13.000000',11,1,1),(41,'2023-12-01 16:18:20.000000','2023-12-01 16:18:20.000000',11,1,1),(42,'2023-12-01 16:19:18.000000','2023-12-01 16:19:18.000000',11,1,1),(43,'2023-12-01 16:19:35.000000','2023-12-01 16:19:35.000000',11,3,2),(44,'2023-12-01 16:20:06.000000','2023-12-01 16:20:06.000000',11,2,1),(45,'2023-12-01 16:20:06.000000','2023-12-01 16:20:06.000000',11,2,1),(46,'2023-12-03 02:04:47.000000','2023-12-03 02:04:47.000000',11,2,1),(47,'2023-12-03 02:40:49.000000','2023-12-03 02:40:49.000000',11,1,2),(48,'2023-12-08 07:30:14.000000','2023-12-08 07:30:14.000000',11,1,1),(49,'2023-12-08 08:12:11.000000','2023-12-08 08:12:11.000000',11,1,1);
/*!40000 ALTER TABLE `cv_skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cv_template`
--

DROP TABLE IF EXISTS `cv_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cv_template` (
  `cv_templateid` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_acquisition` date DEFAULT NULL,
  `cvid` bigint(20) DEFAULT NULL,
  `template_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cv_templateid`),
  KEY `FKos6gcisig5ya6x4pkvnd8q9yx` (`cvid`),
  KEY `FKomh4mw8tvvmlg0pf2gg0bapw4` (`template_id`),
  CONSTRAINT `FKomh4mw8tvvmlg0pf2gg0bapw4` FOREIGN KEY (`template_id`) REFERENCES `ref_template` (`templateid`),
  CONSTRAINT `FKos6gcisig5ya6x4pkvnd8q9yx` FOREIGN KEY (`cvid`) REFERENCES `cv_info` (`cvid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cv_template`
--

LOCK TABLES `cv_template` WRITE;
/*!40000 ALTER TABLE `cv_template` DISABLE KEYS */;
INSERT INTO `cv_template` VALUES (1,'2020-01-01',11,1);
/*!40000 ALTER TABLE `cv_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ref_certificate`
--

DROP TABLE IF EXISTS `ref_certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ref_certificate` (
  `certificateid` bigint(20) NOT NULL AUTO_INCREMENT,
  `certificate_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `organisation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`certificateid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ref_certificate`
--

LOCK TABLES `ref_certificate` WRITE;
/*!40000 ALTER TABLE `ref_certificate` DISABLE KEYS */;
INSERT INTO `ref_certificate` VALUES (1,'Java Developer Certification','Certification for Java programming skills','Java Certification Institute'),(2,'Data Science Professional Certificate','Certification in data science and analytics','Data Science Academy'),(3,'Project Management Professional (PMP)','Certification for project management professionals','Project Management Institute');
/*!40000 ALTER TABLE `ref_certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ref_company`
--

DROP TABLE IF EXISTS `ref_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ref_company` (
  `companyid` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`companyid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ref_company`
--

LOCK TABLES `ref_company` WRITE;
/*!40000 ALTER TABLE `ref_company` DISABLE KEYS */;
INSERT INTO `ref_company` VALUES (1,'123 Main Street, Cityville','ABC Corporation','555-1234'),(2,'456 Oak Avenue, Townburg','XYZ Ltd','555-5678'),(3,'789 Pine Lane, Villagetown','LMN Enterprises','555-9876');
/*!40000 ALTER TABLE `ref_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ref_country`
--

DROP TABLE IF EXISTS `ref_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ref_country` (
  `countryid` bigint(20) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`countryid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ref_country`
--

LOCK TABLES `ref_country` WRITE;
/*!40000 ALTER TABLE `ref_country` DISABLE KEYS */;
INSERT INTO `ref_country` VALUES (1,'United States'),(2,'United Kingdom'),(3,'Canada'),(4,'Germany');
/*!40000 ALTER TABLE `ref_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ref_language`
--

DROP TABLE IF EXISTS `ref_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ref_language` (
  `languageid` bigint(20) NOT NULL AUTO_INCREMENT,
  `language_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`languageid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ref_language`
--

LOCK TABLES `ref_language` WRITE;
/*!40000 ALTER TABLE `ref_language` DISABLE KEYS */;
INSERT INTO `ref_language` VALUES (1,'English'),(2,'Spanish'),(3,'French'),(4,'German');
/*!40000 ALTER TABLE `ref_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ref_level_formation`
--

DROP TABLE IF EXISTS `ref_level_formation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ref_level_formation` (
  `niveau_formationid` bigint(20) NOT NULL AUTO_INCREMENT,
  `niveau_formation_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`niveau_formationid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ref_level_formation`
--

LOCK TABLES `ref_level_formation` WRITE;
/*!40000 ALTER TABLE `ref_level_formation` DISABLE KEYS */;
INSERT INTO `ref_level_formation` VALUES (1,'High School Diploma'),(2,'Associate Degree'),(3,'Bachelor'),(4,'Master');
/*!40000 ALTER TABLE `ref_level_formation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ref_level_language`
--

DROP TABLE IF EXISTS `ref_level_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ref_level_language` (
  `niveau_languageid` bigint(20) NOT NULL AUTO_INCREMENT,
  `niveau_language_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`niveau_languageid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ref_level_language`
--

LOCK TABLES `ref_level_language` WRITE;
/*!40000 ALTER TABLE `ref_level_language` DISABLE KEYS */;
INSERT INTO `ref_level_language` VALUES (1,'Basic'),(2,'Intermediate'),(3,'Advanced');
/*!40000 ALTER TABLE `ref_level_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ref_level_skill`
--

DROP TABLE IF EXISTS `ref_level_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ref_level_skill` (
  `niveau_skillid` bigint(20) NOT NULL AUTO_INCREMENT,
  `niveau_skill_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`niveau_skillid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ref_level_skill`
--

LOCK TABLES `ref_level_skill` WRITE;
/*!40000 ALTER TABLE `ref_level_skill` DISABLE KEYS */;
INSERT INTO `ref_level_skill` VALUES (1,'Beginner'),(2,'Intermediate'),(3,'Advanced');
/*!40000 ALTER TABLE `ref_level_skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ref_mention`
--

DROP TABLE IF EXISTS `ref_mention`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ref_mention` (
  `mentionid` bigint(20) NOT NULL AUTO_INCREMENT,
  `mention_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mentionid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ref_mention`
--

LOCK TABLES `ref_mention` WRITE;
/*!40000 ALTER TABLE `ref_mention` DISABLE KEYS */;
INSERT INTO `ref_mention` VALUES (1,'Excellent'),(2,'Good'),(3,'Average'),(4,'Below Average');
/*!40000 ALTER TABLE `ref_mention` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ref_school`
--

DROP TABLE IF EXISTS `ref_school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ref_school` (
  `ecoleid` bigint(20) NOT NULL AUTO_INCREMENT,
  `ecole_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ecoleid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ref_school`
--

LOCK TABLES `ref_school` WRITE;
/*!40000 ALTER TABLE `ref_school` DISABLE KEYS */;
INSERT INTO `ref_school` VALUES (1,'Primary School Alpha'),(2,'High School Beta'),(3,'Technical Institute Gamma'),(4,'Art Academy Delta');
/*!40000 ALTER TABLE `ref_school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ref_skill`
--

DROP TABLE IF EXISTS `ref_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ref_skill` (
  `skillid` bigint(20) NOT NULL AUTO_INCREMENT,
  `skill_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`skillid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ref_skill`
--

LOCK TABLES `ref_skill` WRITE;
/*!40000 ALTER TABLE `ref_skill` DISABLE KEYS */;
INSERT INTO `ref_skill` VALUES (1,'Programming'),(2,'Data Analysis'),(3,'Graphic Design');
/*!40000 ALTER TABLE `ref_skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ref_template`
--

DROP TABLE IF EXISTS `ref_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ref_template` (
  `templateid` bigint(20) NOT NULL AUTO_INCREMENT,
  `template_data` varchar(255) DEFAULT NULL,
  `template_image` varchar(255) DEFAULT NULL,
  `template_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`templateid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ref_template`
--

LOCK TABLES `ref_template` WRITE;
/*!40000 ALTER TABLE `ref_template` DISABLE KEYS */;
INSERT INTO `ref_template` VALUES (1,'Exemple_A4.jrxml','https://w0.peakpx.com/wallpaper/426/919/HD-wallpaper-vertical-stars-dark-sky-reflection.jpg','info'),(2,'Exemple_A4.jrxml','/','classic');
/*!40000 ALTER TABLE `ref_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'aa@bb.cc','aabbcc',0);
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

-- Dump completed on 2024-02-06 10:48:50
