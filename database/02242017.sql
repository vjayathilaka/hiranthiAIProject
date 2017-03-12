CREATE DATABASE  IF NOT EXISTS `courseselection` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `courseselection`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: courseselection
-- ------------------------------------------------------
-- Server version	5.6.14

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(500) DEFAULT NULL,
  `proposed_intake` int(11) DEFAULT NULL,
  `english` varchar(2) DEFAULT NULL,
  `maths` varchar(2) DEFAULT NULL,
  `schema_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Engineering',1490,'0','0',4),(2,'Engineering (EM)',50,'0','0',4),(3,'Engineering (TM)',50,'0','0',4),(4,'Management',3635,'0','0',2),(5,'Art',5375,'0','0',1);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_data`
--

DROP TABLE IF EXISTS `course_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_data`
--

LOCK TABLES `course_data` WRITE;
/*!40000 ALTER TABLE `course_data` DISABLE KEYS */;
INSERT INTO `course_data` VALUES (1,' Pysical Science1',NULL);
/*!40000 ALTER TABLE `course_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district_zscore`
--

DROP TABLE IF EXISTS `district_zscore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `district_zscore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `district_id` int(11) DEFAULT NULL,
  `zscore` decimal(5,4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district_zscore`
--

LOCK TABLES `district_zscore` WRITE;
/*!40000 ALTER TABLE `district_zscore` DISABLE KEYS */;
INSERT INTO `district_zscore` VALUES (1,1,1,1.9344),(2,1,2,2.0000),(3,1,3,2.0000),(4,1,4,2.0000),(5,1,5,2.0000),(6,1,6,1.0000),(7,1,7,2.0000),(8,1,8,2.0000),(9,1,9,2.0000),(10,1,10,2.0000),(11,1,11,2.0000),(12,1,12,1.0000),(13,1,13,1.0000),(14,1,14,1.0000),(15,1,15,2.0000),(16,1,16,1.0000),(17,1,17,2.0000),(18,1,18,1.0000),(19,1,19,2.0000),(20,1,20,2.0000),(21,1,21,1.0000),(22,1,22,2.0000),(23,1,23,2.0000),(24,1,24,2.0000),(25,2,1,1.9561),(26,2,2,1.6162),(27,2,3,1.7239),(28,2,4,1.5204),(29,2,5,1.6717),(30,2,6,0.9968),(31,2,7,1.9305),(32,2,8,1.9102),(33,2,9,1.6402),(34,2,10,0.9507),(35,2,11,0.4898),(36,2,12,0.0518),(37,2,13,0.1350),(38,2,14,0.0111),(39,2,15,0.8776),(40,2,16,1.0616),(41,2,17,1.2341),(42,2,18,1.0627),(43,2,19,1.8190),(44,2,20,1.4975),(45,2,21,1.3032),(46,2,22,1.6520),(47,2,23,1.6520),(48,2,24,1.5420),(49,3,1,1.8739),(50,3,2,1.6250),(51,3,3,1.7739),(52,3,4,1.5313),(53,3,5,1.6220),(54,3,6,0.8591),(55,3,7,1.7099),(56,3,8,1.8504),(57,3,9,1.6643),(58,3,10,1.7932),(59,3,11,1.2649),(60,3,12,0.0000),(61,3,13,0.4825),(62,3,14,0.1079),(63,3,15,0.4664),(64,3,16,1.0558),(65,3,17,1.0214),(66,3,18,0.7484),(67,3,19,1.7896),(68,3,20,1.4390),(69,3,21,1.3667),(70,3,22,1.5212),(71,3,23,1.5391),(72,3,24,1.4070),(73,4,1,1.5743),(74,4,2,1.5747),(75,4,3,1.5651),(76,4,4,1.4886),(77,4,5,1.4305),(78,4,6,1.3443),(79,4,7,1.4291),(80,4,8,1.3452),(81,4,9,1.2330),(82,4,10,0.5118),(83,4,11,0.6182),(84,4,12,0.3060),(85,4,13,0.7111),(86,4,14,0.7530),(87,4,15,0.2316),(88,4,16,0.4258),(89,4,17,0.5795),(90,4,18,1.3667),(91,4,19,1.5816),(92,4,20,1.4048),(93,4,21,1.4179),(94,4,22,1.5884),(95,4,23,1.4548),(96,4,24,1.4855);
/*!40000 ALTER TABLE `district_zscore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_details`
--

DROP TABLE IF EXISTS `job_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_title` varchar(45) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_details`
--

LOCK TABLES `job_details` WRITE;
/*!40000 ALTER TABLE `job_details` DISABLE KEYS */;
INSERT INTO `job_details` VALUES (1,'Software Engineer',NULL),(4,' Software Engeneer1 ',NULL);
/*!40000 ALTER TABLE `job_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mahout_data`
--

DROP TABLE IF EXISTS `mahout_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mahout_data` (
  `user_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `preference` float NOT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mahout_data`
--

LOCK TABLES `mahout_data` WRITE;
/*!40000 ALTER TABLE `mahout_data` DISABLE KEYS */;
INSERT INTO `mahout_data` VALUES (1,10,1,NULL),(1,11,2,NULL),(1,12,5,NULL),(1,13,5,NULL),(1,14,5,NULL),(1,15,4,NULL),(1,16,5,NULL),(1,17,1,NULL),(1,18,5,NULL),(2,10,1,NULL),(2,11,2,NULL),(2,15,5,NULL),(2,16,4.5,NULL),(2,17,1,NULL),(2,18,5,NULL),(3,11,2.5,NULL),(3,12,4.5,NULL),(3,13,4,NULL),(3,14,3,NULL),(3,15,3.5,NULL),(3,16,4.5,NULL),(3,17,4,NULL),(3,18,5,NULL),(4,10,5,NULL),(4,11,5,NULL),(4,12,5,NULL),(4,13,0,NULL),(4,14,2,NULL),(4,15,3,NULL),(4,16,1,NULL),(4,17,4,NULL),(4,18,1,NULL);
/*!40000 ALTER TABLE `mahout_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offered_universities`
--

DROP TABLE IF EXISTS `offered_universities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offered_universities` (
  `offer_uni_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `uni_id` int(11) NOT NULL,
  PRIMARY KEY (`offer_uni_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offered_universities`
--

LOCK TABLES `offered_universities` WRITE;
/*!40000 ALTER TABLE `offered_universities` DISABLE KEYS */;
INSERT INTO `offered_universities` VALUES (1,1,2),(2,1,5),(3,1,7),(4,1,9),(5,1,6),(6,2,5),(7,3,5),(8,4,1),(9,4,3),(10,4,4),(11,4,6),(12,4,7),(13,4,8),(14,4,9),(15,4,10),(16,4,12),(17,4,11),(18,4,2);
/*!40000 ALTER TABLE `offered_universities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_data`
--

DROP TABLE IF EXISTS `student_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(105) DEFAULT NULL,
  `subject1` int(11) DEFAULT NULL,
  `subject2` int(11) DEFAULT NULL,
  `subject3` int(11) DEFAULT NULL,
  `district` int(11) DEFAULT NULL,
  `zscore` float NOT NULL DEFAULT '0',
  `gender` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `olEnglish` varchar(45) DEFAULT NULL,
  `olMaths` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `attempt` int(11) DEFAULT NULL,
  `selected_course` varchar(45) DEFAULT NULL,
  `university` int(11) DEFAULT NULL,
  `distance` float DEFAULT NULL,
  `sujested_course` varchar(45) DEFAULT NULL,
  `salary` varchar(45) DEFAULT NULL,
  `job_title` varchar(45) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`zscore`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_data`
--

LOCK TABLES `student_data` WRITE;
/*!40000 ALTER TABLE `student_data` DISABLE KEYS */;
INSERT INTO `student_data` VALUES (1,'Vijitha Jayathilaka',1,2,3,2,1.456,'Male',23,'A','A','vijithakn@gmail.com','Kegalle','Kegalle',2,' Pysical Science1',1,NULL,NULL,'130000',' Software Engeneer1 ',4,1),(2,'Hiranthi Ranasinghe',2,3,4,3,1.234,'Female',22,'A','B','vijithakn@gmail.com','Kurunagela','Kurunagale',2,' Pysical Science1',2,NULL,NULL,'45000',' Software Engeneer1 ',4,1);
/*!40000 ALTER TABLE `student_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_com_subjects`
--

DROP TABLE IF EXISTS `sub_com_subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_com_subjects` (
  `sub_com_sub_id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_com_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  PRIMARY KEY (`sub_com_sub_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_com_subjects`
--

LOCK TABLES `sub_com_subjects` WRITE;
/*!40000 ALTER TABLE `sub_com_subjects` DISABLE KEYS */;
INSERT INTO `sub_com_subjects` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,1),(5,2,2),(6,2,3),(7,3,1),(8,3,2),(9,3,3),(10,4,4),(11,4,5),(12,4,6),(13,5,4),(14,5,5),(15,5,6),(16,6,7),(17,6,8),(18,6,9),(19,6,10),(20,6,11),(21,6,12),(22,6,2),(23,6,14),(24,6,15),(25,6,16),(26,6,17),(27,6,3);
/*!40000 ALTER TABLE `sub_com_subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `sub_id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_name` varchar(300) NOT NULL,
  PRIMARY KEY (`sub_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Chemistry'),(2,'Combind Mathamatics'),(3,'Physics'),(4,'Business Studies'),(5,'Economics'),(6,'Accounting'),(7,'Agricultural Science'),(8,'German'),(9,'Elements of Political Science'),(10,'Logic and Scientific Methods'),(11,'Information & Communication Technology'),(12,'Geography'),(14,'English'),(15,'French'),(16,'Business Statistics'),(17,'History');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_com`
--

DROP TABLE IF EXISTS `subject_com`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject_com` (
  `sub_com_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `num_subj` int(11) NOT NULL,
  `second_op_number` int(11) NOT NULL,
  PRIMARY KEY (`sub_com_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_com`
--

LOCK TABLES `subject_com` WRITE;
/*!40000 ALTER TABLE `subject_com` DISABLE KEYS */;
INSERT INTO `subject_com` VALUES (1,1,3,0),(2,2,3,0),(3,3,3,0),(4,4,3,0),(5,4,2,6),(6,4,1,-1);
/*!40000 ALTER TABLE `subject_com` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `university`
--

DROP TABLE IF EXISTS `university`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `university` (
  `uni_id` int(11) NOT NULL AUTO_INCREMENT,
  `uni_name` varchar(500) NOT NULL,
  PRIMARY KEY (`uni_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `university`
--

LOCK TABLES `university` WRITE;
/*!40000 ALTER TABLE `university` DISABLE KEYS */;
INSERT INTO `university` VALUES (1,'University of Colombo'),(2,'University of Peradeniya '),(3,'University of Sri Jayewardenepura'),(4,'University of Kelaniya'),(5,'University of Moratuwa'),(6,'University of Jaffna'),(7,'University of Ruhuna '),(8,'Eastern University, Sri Lanka'),(9,'South Eastern University of Sri Lanka'),(10,'Rajarata University of Sri Lanka'),(11,'Sabaragamuwa University of Sri Lanka'),(12,'Wayamba University of Sri Lanka'),(13,'Uva Wellassa University of Sri Lanka'),(14,'University of the Visual & Performing Arts'),(15,'Sripalee Campus'),(16,'Trincomalee Campus'),(17,'Vavuniya Campus'),(18,'Institute of Indigenous Medicine'),(19,'Gampaha Wickramarachchi Ayurveda Institute'),(20,'University of Colombo School of Computing'),(21,'Swami Vipulananda Institute of Aesthetic Studies, Eastern University, Sri Lanka '),(22,'Ramanathan Academy of Fine Arts, University of Jaffn');
/*!40000 ALTER TABLE `university` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-24  1:10:43
