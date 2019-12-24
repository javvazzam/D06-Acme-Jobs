-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: acme-jobs
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2a5vcjo3stlfcwadosjfq49l1` (`user_account_id`),
  CONSTRAINT `FK_2a5vcjo3stlfcwadosjfq49l1` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (4,0,3);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `more_info` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXnhikaa2dj3la6o2o7e9vo01y0` (`moment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
INSERT INTO `announcement` VALUES (81,0,'2019-11-02 09:35:00.000000',NULL,'This is a description of a sample announcement','Sample announcement 01'),(82,0,'2019-06-09 22:00:00.000000','http://www.example.com','This is a description of another announcement','Sample announcement 02'),(83,0,'1900-10-10 19:00:39.000000','http://www.example.com','This is a description of a very old announcement','Sample announcement 03'),(84,0,'2019-11-19 19:10:00.000000','http://www.example.com','This is a description of a very old announcement','Sample announcement 04'),(85,0,'2019-11-19 18:10:00.000000',NULL,'This is a description of a very old announcement','Sample announcement 05'),(86,0,'2019-11-15 18:10:00.000000','http://www.example.com','This is a description of a very old announcement','Sample announcement 06'),(87,0,'2019-11-16 18:00:00.000000','http://www.example.com','This is a description of a very old announcement','Sample announcement 07');
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anonymous`
--

DROP TABLE IF EXISTS `anonymous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anonymous` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6lnbc6fo3om54vugoh8icg78m` (`user_account_id`),
  CONSTRAINT `FK_6lnbc6fo3om54vugoh8icg78m` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anonymous`
--

LOCK TABLES `anonymous` WRITE;
/*!40000 ALTER TABLE `anonymous` DISABLE KEYS */;
INSERT INTO `anonymous` VALUES (2,0,1);
/*!40000 ALTER TABLE `anonymous` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `justification` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `qualifications` varchar(255) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `skills` varchar(255) DEFAULT NULL,
  `statement` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `job_id` int(11) NOT NULL,
  `worker_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ct7r18vvxl5g4c4k7aefpa4do` (`reference`),
  KEY `IDX2q2747fhp099wkn3j2yt05fhs` (`status`),
  KEY `IDXg54pxa1gngqheaipukeg8jypk` (`moment`),
  KEY `FKoa6p4s2oyy7tf80xwc4r04vh6` (`job_id`),
  KEY `FKmbjdoxi3o93agxosoate4sxbt` (`worker_id`),
  CONSTRAINT `FKmbjdoxi3o93agxosoate4sxbt` FOREIGN KEY (`worker_id`) REFERENCES `worker` (`id`),
  CONSTRAINT `FKoa6p4s2oyy7tf80xwc4r04vh6` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (184,0,NULL,'2019-12-10 00:00:00.000000','You need to have Qualifications Example 1','EEEA-JJJA:WWWA','You need to have Skills Example 1','This is an example 1','pending',141,14),(185,0,NULL,'2019-12-10 23:00:00.000000','You need to have Qualifications Example 2','EEEC-JJJC:WWWB','You need to have Skills Example 2','This is an example 2','accepted',143,17),(186,0,'Intentionally blank','2019-12-09 23:00:00.000000','You need to have Qualifications Example 3','EEEB-JJJB:WWWC','You need to have Skills Example 3','This is an example 3','rejected',142,20),(187,0,NULL,'2019-12-10 02:00:00.000000','You need to have Qualifications Example 4','EEED-JJJD:WWWC','You need to have Skills Example 4','This is an example 4','pending',144,20),(188,0,NULL,'2019-06-09 23:00:00.000000','You need to have Qualifications Example 5','EEED-JJJD:WWWA','You need to have Skills Example 5','This is an example 5','pending',144,14),(189,0,NULL,'2018-07-10 23:00:00.000000','You need to have Qualifications Example 6','EEED-JJJD:WWWE','You need to have Skills Example 6','This is an example 6','accepted',144,26),(190,0,NULL,'2019-12-11 00:00:00.000000','You need to have Qualifications Example 7','EEEB-JJJB:WWWA','You need to have Skills Example 7','This is an example 7','accepted',142,14),(191,0,NULL,'2019-12-11 00:00:00.000000','You need to have Qualifications Example 8','EEEA-JJJA:WWWC','You need to have Skills Example 8','This is an example 8','accepted',141,20),(192,0,'Intentionally blank','2018-07-10 23:00:00.000000','You need to have Qualifications Example 9','EEEG-JJJG:WWWA','You need to have Skills Example 9','This is an example 9','rejected',147,14),(193,0,NULL,'2018-07-10 23:00:00.000000','You need to have Qualifications Example 10','EEEH-JJJH:WWWB','You need to have Skills Example 10','This is an example 10','pending',148,17),(194,0,NULL,'2019-12-01 00:00:00.000000','You need to have Qualifications Example 11','EEEA-JJJA:WWWD','You need to have Skills Example 11','This is an example 11','rejected',141,23),(195,0,NULL,'2019-12-05 00:00:00.000000','You need to have Qualifications Example 12','EEEB-JJJC:WWWB','You need to have Skills Example 12','This is an example 12','rejected',143,23),(196,0,NULL,'2019-12-11 00:00:00.000000','You need to have Qualifications Example 13','EEEA-JJJA:WWWB','You need to have Skills Example 13','This is an example 13','pending',141,17),(197,0,NULL,'2019-11-25 00:00:00.000000','You need to have Qualifications Example 14','EEEB-JJJB:WWWB','You need to have Skills Example 14','This is an example 14','pending',142,17),(198,0,NULL,'2019-11-28 00:00:00.000000','You need to have Qualifications Example 15','EEEA-JJJE:WWWE','You need to have Skills Example 15','This is an example 15','accepted',145,26),(199,0,NULL,'2019-12-04 00:00:00.000000','You need to have Qualifications Example 16','EEEA-JJJE:WWWF','You need to have Skills Example 16','This is an example 16','rejected',145,29),(200,0,NULL,'2019-11-27 00:00:00.000000','You need to have Qualifications Example 17','EEED-JJJF:WWWF','You need to have Skills Example 17','This is an example 17','accepted',146,29),(201,0,NULL,'2019-11-29 00:00:00.000000','You need to have Qualifications Example 18','EEEB-JJJG:WWWE','You need to have Skills Example 18','This is an example 18','pending',147,26),(202,0,NULL,'2019-12-07 00:00:00.000000','You need to have Qualifications Example 19','EEEB-JJJG:WWWG','You need to have Skills Example 19','This is an example 19','accepted',147,32),(203,0,NULL,'2019-12-11 00:00:00.000000','You need to have Qualifications Example 20','EEEA-JJJI:WWWG','You need to have Skills Example 20','This is an example 20','pending',149,32),(204,0,NULL,'2019-12-14 00:00:00.000000','You need to have Qualifications Example 21','EEEA-JJJH:WWWG','You need to have Skills Example 21','This is an example 21','accepted',148,32),(205,0,NULL,'2019-11-29 00:00:00.000000','You need to have Qualifications Example 22','EEEA-JJJJ:WWWC','You need to have Skills Example 22','This is an example 22','pending',150,20);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audit`
--

DROP TABLE IF EXISTS `audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `audit` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `final_mode` bit(1) NOT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `auditor_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXoqmmqbafwj4m9wgwqexcmckjs` (`final_mode`),
  KEY `FK7x4vmrfrh2nyj9mwha7np1ab4` (`auditor_id`),
  KEY `FKijp0sxquetnc9erybuvwrg2e4` (`job_id`),
  CONSTRAINT `FK7x4vmrfrh2nyj9mwha7np1ab4` FOREIGN KEY (`auditor_id`) REFERENCES `auditor` (`id`),
  CONSTRAINT `FKijp0sxquetnc9erybuvwrg2e4` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit`
--

LOCK TABLES `audit` WRITE;
/*!40000 ALTER TABLE `audit` DISABLE KEYS */;
INSERT INTO `audit` VALUES (168,0,'Auditory 1',_binary '','2018-12-21 00:00:00.000000','Title for an audit 1',131,141),(169,0,'Auditory 2',_binary '','2014-02-08 11:10:00.000000','Title for an audit 2',131,142),(170,0,'Auditory 3',_binary '\0','2006-11-06 03:11:00.000000','Title for an audit 3',140,143),(171,0,'Auditory 4',_binary '\0','2015-05-01 14:05:00.000000','Title for an audit 4',134,144),(172,0,'Auditory 5',_binary '','2009-02-04 14:02:00.000000','Title for an audit 5',136,145),(173,0,'Auditory 6',_binary '','2015-10-06 09:10:00.000000','Title for an audit 6',138,141),(174,0,'Auditory 7',_binary '','2016-04-25 02:15:00.000000','Title for an audit 7',140,146),(175,0,'Auditory 8',_binary '\0','2014-02-08 11:10:00.000000','Title for an audit 8',131,147),(176,0,'Auditory 9',_binary '','2006-11-06 03:11:00.000000','Title for an audit 9',134,148),(177,0,'Auditory 10',_binary '','2015-05-01 14:05:00.000000','Title for an audit 10',136,147),(178,0,'Auditory 11',_binary '','2008-02-07 16:02:00.000000','Title for an audit 11',138,146),(179,0,'Auditory 12',_binary '\0','2012-10-06 09:10:00.000000','Title for an audit 12',140,145),(180,0,'Auditory 13',_binary '','2016-11-06 03:11:00.000000','Title for an audit 13',134,144),(181,0,'Auditory 14',_binary '\0','2008-05-01 10:05:00.000000','Title for an audit 14',136,143),(182,0,'Auditory 15',_binary '','2007-02-07 16:02:00.000000','Title for an audit 15',138,142),(183,0,'Auditory 16',_binary '','2001-12-06 05:14:00.000000','Title for an audit 16',140,141);
/*!40000 ALTER TABLE `audit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auditor`
--

DROP TABLE IF EXISTS `auditor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auditor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `firm` varchar(255) DEFAULT NULL,
  `request` bit(1) NOT NULL,
  `statement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_clqcq9lyspxdxcp6o4f3vkelj` (`user_account_id`),
  CONSTRAINT `FK_clqcq9lyspxdxcp6o4f3vkelj` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditor`
--

LOCK TABLES `auditor` WRITE;
/*!40000 ALTER TABLE `auditor` DISABLE KEYS */;
INSERT INTO `auditor` VALUES (131,0,130,'aceptame porfa','Auditor 1(firma)',_binary '','statement 1'),(134,0,133,'aceptame porfa','Auditor 2(firma)',_binary '','statement 2'),(136,0,135,'aceptame porfa','Auditor 3(firma)',_binary '','statement 3'),(138,0,137,'aceptame porfa','Auditor 4(firma)',_binary '','statement 4'),(140,0,139,'aceptame porfa','Auditor 5(firma)',_binary '','statement 5');
/*!40000 ALTER TABLE `auditor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authenticated`
--

DROP TABLE IF EXISTS `authenticated`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authenticated` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h52w0f3wjoi68b63wv9vwon57` (`user_account_id`),
  CONSTRAINT `FK_h52w0f3wjoi68b63wv9vwon57` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authenticated`
--

LOCK TABLES `authenticated` WRITE;
/*!40000 ALTER TABLE `authenticated` DISABLE KEYS */;
INSERT INTO `authenticated` VALUES (5,0,3),(8,0,7),(10,0,9),(12,0,11),(15,0,13),(18,0,16),(21,0,19),(24,0,22),(27,0,25),(30,0,28),(33,0,31),(36,0,34),(39,0,37),(42,0,40),(45,0,43),(48,0,46),(51,0,49),(54,0,52),(57,0,55),(60,0,58),(63,0,61),(66,0,64),(132,0,130);
/*!40000 ALTER TABLE `authenticated` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `sponsor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjoxwdnjr54soq3j89kt3fgrtj` (`sponsor_id`),
  CONSTRAINT `FKjoxwdnjr54soq3j89kt3fgrtj` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge`
--

DROP TABLE IF EXISTS `challenge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenge` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `bronze_goal` varchar(255) DEFAULT NULL,
  `bronze_reward_amount` double DEFAULT NULL,
  `bronze_reward_currency` varchar(255) DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gold_goal` varchar(255) DEFAULT NULL,
  `gold_reward_amount` double DEFAULT NULL,
  `gold_reward_currency` varchar(255) DEFAULT NULL,
  `silver_goal` varchar(255) DEFAULT NULL,
  `silver_reward_amount` double DEFAULT NULL,
  `silver_reward_currency` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXnr284tes3x8hnd3h716tmb3fr` (`deadline`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge`
--

LOCK TABLES `challenge` WRITE;
/*!40000 ALTER TABLE `challenge` DISABLE KEYS */;
INSERT INTO `challenge` VALUES (74,0,'Sing in your bathroom',1,'€','2020-12-09 23:00:00.000000','Singing challenge','Sing in public',10,'€','Sing to your friends',5,'€','Singing challenge'),(75,0,'Jump in your bathroom',1,'€','2022-12-09 23:00:00.000000','Jumping challenge','Jump in public',10,'€','Jump to your friends',5,'€','Jumping challenge'),(76,0,'Dab to yourself',1,'€','2021-12-09 23:00:00.000000','Dabbing challenge','Dab on them haters',10,'€','Dab on your dog',5,'€','Dabbing challenge'),(77,0,'Dab to yourself',1,'€','2021-12-09 23:00:00.000000','Challengue 4','Dab on them haters',10,'€','Dab on your dog',5,'€','Dabbing challenge'),(78,0,'Dab to yourself',1,'€','2021-12-09 23:00:00.000000','Challengue 5','Dab on them haters',10,'€','Dab on your dog',5,'€','Dabbing challenge'),(79,0,'Dab to yourself',1,'€','2021-12-09 23:00:00.000000','Challengue 6','Dab on them haters',10,'€','Dab on your dog',5,'€','Dabbing challenge'),(80,0,'Dab to yourself',1,'€','2021-12-09 23:00:00.000000','Challengue 7','Dab on them haters',10,'€','Dab on your dog',5,'€','Dabbing challenge');
/*!40000 ALTER TABLE `challenge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commercial_banner`
--

DROP TABLE IF EXISTS `commercial_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commercial_banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `sponsor_id` int(11) NOT NULL,
  `credit_card` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_q9id3wc65gg49afc5tlr1c00n` (`sponsor_id`),
  CONSTRAINT `FK_q9id3wc65gg49afc5tlr1c00n` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commercial_banner`
--

LOCK TABLES `commercial_banner` WRITE;
/*!40000 ALTER TABLE `commercial_banner` DISABLE KEYS */;
INSERT INTO `commercial_banner` VALUES (116,0,'http://piezaapieza.net/wp-content/uploads/2016/11/EDU84131.01.jpg','Edu Mig','https://www.atptour.com/',56,'4111111111111111'),(117,0,'https://s3.eestatic.com/2019/09/23/ciencia/Otono-Ciencia_431466954_134482819_1024x576.jpg','The autum in Spain ','https://www.elperiodico.com/es/sociedad/20190918/otono-2019-equinoccio-dia-hora-7639468',56,'4111111111111111'),(118,0,'http://piezaapieza.net/wp-content/uploads/2016/11/EDU84131.01.jpg','Work as anyone','https://www.atptour.com/',56,'4111111111111111'),(119,0,'http://datos.jpg','repeticion1!','https://estosondatosparaiterar.com/',56,'4111111111111111'),(120,0,'http://piezaapieza.net/wp-content/uploads/2016/11/EDU84131.01.jpg','repeticion2!','https://www.atptour.com/',65,'5500000000000004'),(121,0,'http://piezaapieza.net/wp-content/uploads/2016/11/EDU84131.01.jpg','repeticion3!','https://www.atptour.com/',65,'5500000000000004'),(122,0,'http://piezaapieza.net/wp-content/uploads/2016/11/EDU84131.01.jpg','repeticion4!','https://www.atptour.com/',65,'5500000000000004');
/*!40000 ALTER TABLE `commercial_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `activities` varchar(255) DEFAULT NULL,
  `ceo` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `incorporated` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  `stars` double DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX3vwg77973akwy9ilnfq707yt1` (`stars`),
  KEY `IDXbm7mwffwxwiukjmbmt9t1qnnu` (`sector`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (109,0,'Activity1','Ceo1','email1@email.com',_binary '','Name1 Inc','+123 987654321','Aerospace',4.6,'https://www.site1.com/'),(110,0,'Activity2','Ceo2','email2@email.com',_binary '','Name2 Inc','+123 (1234) 6543212','Food',2.1,'https://www.site2.com/'),(111,0,'Activity3','Ceo3','email3@email.com',_binary '\0','Name3 LLC','+123 (1234) 6543212','Food',NULL,'https://www.site3.com/'),(112,0,'Activity4','Ceo4','email4@email.com',_binary '\0','Name4 LLC','+123 (0) 9876543210','Food',5,'https://www.site4.com/'),(113,0,'Activity5','Ceo4','email5@email.com',_binary '','Name5 Inc','+123 (0) 9876543210','Software',5,'https://www.site5.com/'),(114,0,'Activity6','Ceo6','email6@email.com',_binary '','Name6 Inc','+12 (25) 987654301','Automotive',4.2,'https://www.site6.com/'),(115,0,'Activity7','Ceo7','email6@email.com',_binary '\0','Name7 LLC','+12 (25) 987654301','Automotive',4.2,'https://www.site6.com/');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumer`
--

DROP TABLE IF EXISTS `consumer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6cyha9f1wpj0dpbxrrjddrqed` (`user_account_id`),
  CONSTRAINT `FK_6cyha9f1wpj0dpbxrrjddrqed` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumer`
--

LOCK TABLES `consumer` WRITE;
/*!40000 ALTER TABLE `consumer` DISABLE KEYS */;
/*!40000 ALTER TABLE `consumer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customization`
--

DROP TABLE IF EXISTS `customization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customization` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `spam` varchar(255) DEFAULT NULL,
  `threshold` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customization`
--

LOCK TABLES `customization` WRITE;
/*!40000 ALTER TABLE `customization` DISABLE KEYS */;
INSERT INTO `customization` VALUES (6,0,'sex,hardcore,viagra,cialis,nigeria,you\'ve won,million dollar,   sexo,duro,viagra,cialis,nigeria,has ganado,millón de euros',1);
/*!40000 ALTER TABLE `customization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `duty`
--

DROP TABLE IF EXISTS `duty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `duty` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `time_amount` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs2uoxh4i5ya8ptyefae60iao1` (`job_id`),
  CONSTRAINT `FKs2uoxh4i5ya8ptyefae60iao1` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `duty`
--

LOCK TABLES `duty` WRITE;
/*!40000 ALTER TABLE `duty` DISABLE KEYS */;
INSERT INTO `duty` VALUES (151,0,'Description of a duty',60.75,'Duty title sample',141),(152,0,'Description of a duty2',40,'Duty title sample2',142),(153,0,'Description of a duty3',40,'Duty title sample3',142),(154,0,'Description of a duty4',10,'Duty title sample4',142),(155,0,'Description of a duty5',10,'Duty title sample5',142),(156,0,'Description of a duty6',30,'Duty title sample6',143),(157,0,'Description of a duty7',70,'Duty title sample5',143),(158,0,'Description of a duty8',10.8,'Duty title sample8',144),(159,0,'Description of a duty9',20,'Duty title sample9',145),(160,0,'Description of a duty10',10,'Duty title sample10',145),(161,0,'Description of a duty11',70,'Duty title sample11',145),(162,0,'Description of a duty12',60,'Duty title sample12',146),(163,0,'Description of a duty13',20,'Duty title sample13',146),(164,0,'Description of a duty14',20,'Duty title sample14',146),(165,0,'Description of a duty15',50,'Duty title sample15',147),(166,0,'Description of a duty16',50,'Duty title sample16',147),(167,0,'Description of a duty17',100,'Duty title sample17',148);
/*!40000 ALTER TABLE `duty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employer`
--

DROP TABLE IF EXISTS `employer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_na4dfobmeuxkwf6p75abmb2tr` (`user_account_id`),
  CONSTRAINT `FK_na4dfobmeuxkwf6p75abmb2tr` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employer`
--

LOCK TABLES `employer` WRITE;
/*!40000 ALTER TABLE `employer` DISABLE KEYS */;
INSERT INTO `employer` VALUES (35,0,34,'Employer 1, Inc.','Sector 1'),(38,0,37,'Employer 2, Inc.','Sector 2'),(41,0,40,'Employer 3, Inc.','Sector 3'),(44,0,43,'Employer 4, Inc.','Sector 4'),(47,0,46,'Employer 5, Inc.','Sector 5'),(50,0,49,'Employer 6, Inc.','Sector 6'),(53,0,52,'Employer 7, Inc.','Sector 7');
/*!40000 ALTER TABLE `employer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (244);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investor`
--

DROP TABLE IF EXISTS `investor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `investor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  `stars` double DEFAULT NULL,
  `statement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXq1q335kxox0leg1u9hhndvue1` (`stars`),
  KEY `IDX1slmmcr1g0wv9jbgun6rny0oy` (`sector`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investor`
--

LOCK TABLES `investor` WRITE;
/*!40000 ALTER TABLE `investor` DISABLE KEYS */;
INSERT INTO `investor` VALUES (102,0,'Person 2','Software',3.8,'Esto es una declaración de inversión 1'),(103,0,'Person 1','Aerospace',0.5,'Esto es una declaración de inversión 2'),(104,0,'Person 3','Food',NULL,'Esto es una declaración de inversión 3'),(105,0,'Person 4','Automotive',5,'Esto es una declaración de inversión 4'),(106,0,'Person 5','Food',4.4,'Esto es una declaración de inversión 5'),(107,0,'Person 6','Aerospace',5,'Esto es una declaración de inversión 6.'),(108,0,'Person 7','Aerospace',5,'Esto es una declaración de inversión 7.');
/*!40000 ALTER TABLE `investor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `final_mode` bit(1) NOT NULL,
  `more_info` varchar(255) DEFAULT NULL,
  `reference_number` varchar(255) DEFAULT NULL,
  `salary_amount` double DEFAULT NULL,
  `salary_currency` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `employer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bos0omdc9s5vykasqjhwaq65m` (`reference_number`),
  KEY `IDXfdmpnr8o4phmk81sqsano16r` (`deadline`),
  KEY `IDXt84ibbldao4ngscmvo7ja0es` (`final_mode`),
  KEY `FK3rxjf8uh6fh2u990pe8i2at0e` (`employer_id`),
  CONSTRAINT `FK3rxjf8uh6fh2u990pe8i2at0e` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (141,0,'2030-12-11 22:59:00.000000','Description sample of a Descriptor',_binary '\0','http://www.moreinfo.com/','EEEA-JJJA',15000.98,'€','Title 1',35),(142,0,'2030-12-11 22:59:00.000000','Description sample of a Descriptor',_binary '','http://www.moreinfo.com/','EEEB-JJJB',20000,'€','Title 2',38),(143,0,'2023-10-10 18:51:00.000000','Description sample of a Descriptor',_binary '','http://www.moreinfo.com/','EEEC-JJJC',31000,'€','Title 3',38),(144,0,'2024-11-06 09:34:00.000000','Description sample of a Descriptor',_binary '\0','http://www.moreinfo.com/','EEED-JJJD',16000,'€','Title 4',35),(145,0,'2085-04-25 04:57:00.000000','Description sample of a Descriptor',_binary '','http://www.moreinfo.com/','EEEE-JJJE',14000,'€','Title 5',35),(146,0,'2035-04-25 04:57:00.000000','Description sample of a Descriptor',_binary '','http://www.moreinfo.com/','EEEF-JJJF',14000,'€','Title 6',44),(147,0,'1999-04-25 04:57:00.000000','Description sample of a Descriptor',_binary '','http://www.moreinfo.com/','EEEG-JJJG',15000,'€','Title 7',38),(148,0,'1999-04-25 04:57:00.000000','Description sample of a Descriptor',_binary '\0','http://www.moreinfo.com/','EEEH-JJJH',11000,'€','Title 8',35),(149,0,'1999-04-25 04:57:00.000000','Description sample of a Descriptor',_binary '\0','http://www.moreinfo.com/','ENAP-JJJP',11000,'€','Title 9',35),(150,0,'1999-04-25 04:57:00.000000','Description sample of a Descriptor',_binary '\0','http://www.moreinfo.com/','EMOP-HHJP',11000,'€','Title 10',35);
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `thread_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK28hjkn063wrsjuiyyf8sm3s2v` (`thread_id`),
  KEY `FKik4epe9dp5q6uenarfyia7xin` (`user_id`),
  CONSTRAINT `FK28hjkn063wrsjuiyyf8sm3s2v` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`),
  CONSTRAINT `FKik4epe9dp5q6uenarfyia7xin` FOREIGN KEY (`user_id`) REFERENCES `authenticated` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (212,0,'Message01','2018-12-10 00:00:00.000000','discussion,employees','Title for a message 1',206,8),(213,0,'Message02','2018-12-20 00:00:00.000000','design,agile','Title for a message 2',207,8),(214,0,'Message03','2018-12-21 00:00:00.000000',NULL,'Title for a message 3',208,12),(215,0,'Message04','2019-06-21 09:00:00.000000',NULL,'Title for a message 4',209,8),(216,0,'Message05','2018-12-18 00:50:00.000000','computer,science','Title for a message 5',206,10),(217,0,'Message06','2018-02-20 10:05:00.000000','future,company,forecast','Title for a message 6',207,8),(218,0,'Message07','2017-12-21 01:10:00.000000',NULL,'Title for a message 7',208,8),(219,0,'Message08','2016-07-20 09:07:00.000000',NULL,'Title for a message 8',209,10),(220,0,'Message09','2018-02-20 10:05:00.000000','future,company,forecast','Title for a message 9',206,12),(221,0,'Message10','2008-08-25 12:08:00.000000',NULL,'Title for a message 10',207,10),(222,0,'Message11','2009-07-13 17:07:00.000000',NULL,'Title for a message 11',208,12),(223,0,'Message11','2009-07-13 17:07:00.000000',NULL,'Title for a message 12',209,15),(224,0,'Message13','2005-07-13 17:07:00.000000',NULL,'Title for a message 13',206,36),(225,0,'Message14','2015-12-10 17:05:00.000000',NULL,'Title for a message 14',207,132),(226,0,'Message15','2019-12-01 17:05:00.000000',NULL,'Title for a message 15',211,60),(227,0,'Message16','2019-06-01 14:05:00.000000',NULL,'Title for a message 16',211,57);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `non_commercial_banner`
--

DROP TABLE IF EXISTS `non_commercial_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `non_commercial_banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `sponsor_id` int(11) NOT NULL,
  `jingle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2l8gpcwh19e7jj513or4r9dvb` (`sponsor_id`),
  CONSTRAINT `FK_2l8gpcwh19e7jj513or4r9dvb` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `non_commercial_banner`
--

LOCK TABLES `non_commercial_banner` WRITE;
/*!40000 ALTER TABLE `non_commercial_banner` DISABLE KEYS */;
INSERT INTO `non_commercial_banner` VALUES (123,0,'https://miro.medium.com/max/1024/1*vxjAHkrXbGG6gOiPZgjeZA.jpeg','Conocimiento es poder','https://neoattack.com/',59,'https://neoattack.com/'),(124,0,'https://media.istockphoto.com/illustrations/wind-icon-illustration-id912304034','wild is the wind','https://www.tiempo.com/montequinto.htm',59,NULL),(125,0,'http://expertolaboralonline.jpg','Sundays means no work','https://m.media-amazon.com',59,NULL),(126,0,'http://masdatos.jpg','Repeticion1!','https://arepetirdatos.com',59,NULL),(127,0,'http://iteracion.jpg','Repeticion2!','https://iteraciondedatos.com',65,NULL),(128,0,'http://otroejemplo.jpg','Repeticion3!','https://ejemplos.com',65,NULL),(129,0,'http://estamosañadiendodatosparaiterar.jpg','Repeticion4!','https://yesteesotrodatomas.com',65,NULL);
/*!40000 ALTER TABLE `non_commercial_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer`
--

DROP TABLE IF EXISTS `offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `max_amount` double DEFAULT NULL,
  `max_currency` varchar(255) DEFAULT NULL,
  `min_amount` double DEFAULT NULL,
  `min_currency` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iex7e8fs0fh89yxpcnm1orjkm` (`ticker`),
  KEY `IDXq2o9psuqfuqmq59f0sq57x9uf` (`deadline`),
  KEY `IDXcp4664f36sgqsd0ihmirt0w0` (`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer`
--

LOCK TABLES `offer` WRITE;
/*!40000 ALTER TABLE `offer` DISABLE KEYS */;
INSERT INTO `offer` VALUES (95,0,'2022-08-10 08:35:00.000000',2500,'€',1800.2,'€','2019-06-10 08:35:00.000000','This is a description of a sample offer','OAAAA-00000','Sample offer 01'),(96,0,'2019-10-10 10:00:00.000000',1000.5,'€',65,'€','2019-06-09 22:00:00.000000','This is a description of another offer','OBBBB-00000','Sample offer 02'),(97,0,'1901-10-10 19:00:39.000000',1300.5,'€',1000.5,'€','1900-10-10 19:00:39.000000','This is a description of a very old offer','OCCCC-00000','Sample offer 03'),(98,0,'2019-12-12 09:35:00.000000',650.98,'€',400.2,'€','2019-06-10 08:35:00.000000','This is a description of a sample offer','ODDDD-00000','Sample offer 04'),(99,0,'2019-12-12 11:00:00.000000',1530.5,'€',300.85,'€','2019-06-09 22:00:00.000000','This is a description of another offer','OEEEE-00000','Sample offer 05'),(100,0,'2019-12-12 11:00:00.000000',1530.5,'€',300.85,'€','2019-06-09 22:00:00.000000','This is a description of another offer','OFFFF-00000','Sample offer 06'),(101,0,'2019-12-12 11:00:00.000000',1530.5,'€',300.85,'€','2019-06-09 22:00:00.000000','This is a description of another offer','OGGGG-00000','Sample offer 07');
/*!40000 ALTER TABLE `offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participant`
--

DROP TABLE IF EXISTS `participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participant` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `thread_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8mocuiguc2q27excsu2w2q9ai` (`thread_id`),
  KEY `FK67h73ib586xy9hvw4vyy75fvv` (`user_id`),
  CONSTRAINT `FK67h73ib586xy9hvw4vyy75fvv` FOREIGN KEY (`user_id`) REFERENCES `authenticated` (`id`),
  CONSTRAINT `FK8mocuiguc2q27excsu2w2q9ai` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participant`
--

LOCK TABLES `participant` WRITE;
/*!40000 ALTER TABLE `participant` DISABLE KEYS */;
INSERT INTO `participant` VALUES (228,0,206,8),(229,0,207,8),(230,0,208,12),(231,0,209,8),(232,0,206,10),(233,0,207,18),(234,0,208,8),(235,0,209,10),(236,0,206,12),(237,0,207,10),(238,0,208,10),(239,0,209,15),(240,0,206,36),(241,0,207,132),(242,0,211,60),(243,0,211,57);
/*!40000 ALTER TABLE `participant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provider` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b1gwnjqm6ggy9yuiqm0o4rlmd` (`user_account_id`),
  CONSTRAINT `FK_b1gwnjqm6ggy9yuiqm0o4rlmd` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shout`
--

DROP TABLE IF EXISTS `shout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shout` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shout`
--

LOCK TABLES `shout` WRITE;
/*!40000 ALTER TABLE `shout` DISABLE KEYS */;
INSERT INTO `shout` VALUES (67,0,'Eduardo Miguel Botía Domingo','2019-09-03 09:41:00.000000','Mensaje de prueba 1'),(68,0,'Eduardo Miguel Botía Domingo','2019-08-02 21:48:00.000000','Mensaje de prueba 2'),(69,0,'Eduardo Miguel Botía Domingo','2019-02-03 13:32:00.000000','Mensaje de prueba 3'),(70,0,'Eduardo Miguel Botía Domingo','2019-04-21 16:55:00.000000','Mensaje de prueba 4'),(71,0,'Eduardo Miguel Botía Domingo','2019-09-03 17:42:00.000000','Mensaje de prueba 5'),(72,0,'Eduardo Miguel Botía Domingo','2019-09-03 17:42:00.000000','Mensaje de prueba 6'),(73,0,'Eduardo Miguel Botía Domingo','2019-09-03 17:42:00.000000','Mensaje de prueba 7');
/*!40000 ALTER TABLE `shout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud`
--

DROP TABLE IF EXISTS `solicitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitud` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `reward_amount` double DEFAULT NULL,
  `reward_currency` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rea5aivw0b4fiu93s509u9lky` (`ticker`),
  KEY `IDXd37rv1cyj5dvbd63k6progy7q` (`deadline`),
  KEY `IDX2qy5jkiqwk6f13kkfq8pu61le` (`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud`
--

LOCK TABLES `solicitud` WRITE;
/*!40000 ALTER TABLE `solicitud` DISABLE KEYS */;
INSERT INTO `solicitud` VALUES (88,0,'2019-08-10 08:35:00.000000','2019-06-10 08:35:00.000000',40.2,'€','This is a description of a sample request','RAAAA-00000','Sample request 01'),(89,0,'2019-10-10 10:00:00.000000','2019-06-09 22:00:00.000000',65,'€','This is a description of another request','RBBBB-00000','Sample request 02'),(90,0,'1901-10-10 19:00:39.000000','1900-10-10 19:00:39.000000',100.5,'€','This is a description of a very old request','RCCCC-00000','Sample request 03'),(91,0,'2023-03-08 05:35:00.000000','2019-06-10 08:35:00.000000',450.2,'€','This is a description of a sample request','RDDDD-00000','Sample request 04'),(92,0,'2023-05-04 17:00:00.000000','2019-06-09 22:00:00.000000',680.9,'€','This is a description of another request','REEEE-00000','Sample request 05'),(93,0,'2023-05-04 17:00:00.000000','2019-06-09 22:00:00.000000',680.9,'€','This is a description of another request','RFFFF-00000','Sample request 06'),(94,0,'2023-05-04 17:00:00.000000','2019-06-09 22:00:00.000000',680.9,'€','This is a description of another request','RGGGG-00000','Sample request 07');
/*!40000 ALTER TABLE `solicitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsor`
--

DROP TABLE IF EXISTS `sponsor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sponsor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `credit_card` varchar(255) DEFAULT NULL,
  `organization` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_20xk0ev32hlg96kqynl6laie2` (`user_account_id`),
  CONSTRAINT `FK_20xk0ev32hlg96kqynl6laie2` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsor`
--

LOCK TABLES `sponsor` WRITE;
/*!40000 ALTER TABLE `sponsor` DISABLE KEYS */;
INSERT INTO `sponsor` VALUES (56,0,55,'4111111111111111','Acme organization 1'),(59,0,58,NULL,'Acme organization 2'),(62,0,61,'6011000000000004','Acme organization 3'),(65,0,64,'5500000000000004','Acme organization 4');
/*!40000 ALTER TABLE `sponsor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thread`
--

DROP TABLE IF EXISTS `thread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thread` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thread`
--

LOCK TABLES `thread` WRITE;
/*!40000 ALTER TABLE `thread` DISABLE KEYS */;
INSERT INTO `thread` VALUES (206,0,'2010-12-01 22:59:00.000000','Sample thread 1'),(207,0,'2015-08-02 19:12:00.000000','Sample thread 2'),(208,0,'2017-09-02 03:25:00.000000','Sample thread 3'),(209,0,'2014-12-11 22:59:00.000000','Sample thread 4'),(210,0,'2017-06-11 21:59:00.000000','Sample thread 5'),(211,0,'2019-06-11 11:59:00.000000','Sample thread 6');
/*!40000 ALTER TABLE `thread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `identity_email` varchar(255) DEFAULT NULL,
  `identity_name` varchar(255) DEFAULT NULL,
  `identity_surname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,0,_binary '\0','john.doe@acme.com','John','Doe','$2a$05$XhHX16yie357noIEUR/gHuV.Lva4F1Mshwj8q6TaSBI/cj1o9WYdG','anonymous'),(3,0,_binary '','administrator@acme.com','Administrator','Acme.com','$2a$05$Eoh9c8fbm0zA1EN85wSPAuDZFDbjYa.A5.PMEILf8jEY6lLaTbAZy','administrator'),(7,0,_binary '','authenticated@acme.com','Authenticated','User','$2a$05$v9ai0mHE8nLXdK5i/nh/9OwAYoehiIXcwO5QIAnUDQ8XOO9ibh0ty','authenticated1'),(9,0,_binary '','authenticated2@acme.com','Authenticated2','User2','$2a$05$4IzFaXP6qD8aafuCXPz.quLEXEVMzVTLQnLozlTun5A/PbnihOIh2','authenticated2'),(11,0,_binary '','authenticated3@acme.com','Authenticated3','User3','$2a$05$EsugOINz0NiblTM9XwqESejUKxTPiX7tPG5GTday76Yx5.FIK5aEq','authenticated3'),(13,0,_binary '','worker1@acme.com','Worker','One','$2a$05$qIGf65hcYrTydEptHIifDehseuL5.QsWoB69qHfCjjnusyDmx13ty','worker1'),(16,0,_binary '','worker2@acme.com','Worker','Two','$2a$05$L8UpJPTNQz8Cc3JELuWLw.Q4.oeu7OmbfiW/m8/1OexfSLVdC3bp.','worker2'),(19,0,_binary '','worker3@acme.com','Worker','Three','$2a$05$5CPrVRnqKL/laAtz4nmAsOsNSvy1qR9RB.wqRng9h6G5j29bZL42a','worker3'),(22,0,_binary '','worker4@acme.com','Worker','Four','$2a$05$2F7aOrZhg3by4MP0C378e.JIko6zCHownB0rkNn1orPrtbGLKyU7m','worker4'),(25,0,_binary '','worker5@acme.com','Worker','Five','$2a$05$FZIDJHolIKFhZsII/ovqA.sA9n0QuHZ4Ed42Rj1p8dzFGv1dikQW2','worker5'),(28,0,_binary '','worker6@acme.com','Worker','Six','$2a$05$ZgoBDMgMx61lhX2oiD2hw.D..EJHlQRTpQAv2SVPJWmgrkKoLLoTK','worker6'),(31,0,_binary '','worker7@acme.com','Worker','Seven','$2a$05$33uurfPFj2PmEN5CiUCzLOKZ6SZ4gTa19tKzULwnOugz6HrVq1oiq','worker7'),(34,0,_binary '','employer1@acme.com','Employer','One','$2a$05$ahqnl8Tnp29w.wTFPCbumesMvtcEUvqxyT4j0lfPFp8ztAXTA7VdK','employer1'),(37,0,_binary '','employer2@acme.com','Employer','Two','$2a$05$CGwlU3UevRXsDzaVKeVwvuKmD0OvCXQtV47nuzHjgs92kcAUmLRN.','employer2'),(40,0,_binary '','employer3@acme.com','Employer','Three','$2a$05$M3JEHuWiub0jQgz/iouC9uV4UuxQJRMLOPp0M6FOOAYcJByUG.wr2','employer3'),(43,0,_binary '','employer4@acme.com','Employer','Four','$2a$05$HzgI3.6ZxKLn5fXlOlRV1.EhFKHVARpN4aTMQZj33HHu/.93JMCQ2','employer4'),(46,0,_binary '','employer5@acme.com','Employer','Five','$2a$05$H9zYF0dzAn9gj3B5UOqfFuVWhSxLA6DSUX3QjU51CuhWDIIBTWIR6','employer5'),(49,0,_binary '','employer6@acme.com','Employer','Six','$2a$05$5CNq7suYfeD6R.tp0sVdSenzBluQHZPqF38iJDoRRlFY6REQw9KD6','employer6'),(52,0,_binary '','employer7@acme.com','Employer','Seven','$2a$05$9NoUqFuxqnBSk5SlwjpyhORY9XskZo/CRD4qnF88rdeC/y/9SCSuu','employer7'),(55,0,_binary '','sponsor1@acme.com','Sponsor','One','$2a$05$jnOUXw/2kiA7bU2dtNeBcud9hQK1K63VcpJDbbptoDgFGb5IS7IvW','sponsor1'),(58,0,_binary '','sponsor2@acme.com','Sponsor','Two','$2a$05$VuEjW.uxH84OQqRG6OvcmOMvggGjyVN38/wiPYdl27k5ghAR6q192','sponsor2'),(61,0,_binary '','sponsor3@acme.com','Sponsor','Three','$2a$05$iKXnJlGRnVidTF82xeKdzu.JKTJ2Gsc2bfInYeqCic2w6F6SYaB9.','sponsor3'),(64,0,_binary '','sponsor4@acme.com','Sponsor','Four','$2a$05$/G8eBtl9EAreGjDPue3kGuYWm3O4rZ/7JijWBdE2vN6ej3UWH.Cpa','sponsor4'),(130,0,_binary '','auditor1@acme.com','Auditor','One','$2a$05$kcyev8MnEKO3GjTBgFLU4eQ6LP.ksU.KyrYEBpjr/gw2NelCyMIuC','auditor1'),(133,0,_binary '','auditor2@acme.com','Auditor','Second','$2a$05$dQxVLnzu6Sq2iENx1LcHeOw9Ab33s0nHznU2Z5EId.GhcjTLbtVrm','auditor2'),(135,0,_binary '','auditor3@acme.com','Auditor','Third','$2a$05$NDorPv8i3XU0SknAeNzh1.EHXGolCuT4gMGKps3n0npQuh5uKj7gK','auditor3'),(137,0,_binary '','auditor4@acme.com','Auditor','Fourth','$2a$05$fZ3ZTUJyB1TrhDayGl24v.vNdRgGiWG0LHjl6T9wop0BZiVkTlUMe','auditor4'),(139,0,_binary '','auditor5@acme.com','Auditor','Fifth','$2a$05$fiEZgP5OLoqrVBQ4TfHteeeQUNEkT6uWD3Bj5j3i53qEWMWO79zLi','auditor5');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `worker` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `qualifications` varchar(255) DEFAULT NULL,
  `skills` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_l5q1f33vs2drypmbdhpdgwfv3` (`user_account_id`),
  CONSTRAINT `FK_l5q1f33vs2drypmbdhpdgwfv3` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES (14,0,13,'Example 1 Qualifications Records','Example 1 Skills Records'),(17,0,16,'Example 2 Qualifications Records','Example 2 Skills Records'),(20,0,19,'Example 3 Qualifications Records','Example 3 Skills Records'),(23,0,22,'Example 4 Qualifications Records','Example 4 Skills Records'),(26,0,25,'Example 5 Qualifications Records','Example 5 Skills Records'),(29,0,28,'Example 6 Qualifications Records','Example 6 Skills Records'),(32,0,31,'Example 7 Qualifications Records','Example 7 Skills Records');
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-18 10:09:10
