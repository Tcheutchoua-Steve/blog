-- MySQL dump 10.13  Distrib 5.6.12, for Win64 (x86_64)
--
-- Host: localhost    Database: myblog
-- ------------------------------------------------------
-- Server version	5.6.12-log

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
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `postedAt` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,'This is the content','2014-08-04 14:21:59','My title'),(2,'This is the content','2014-08-04 14:26:34','My title'),(5,'This is the content','2014-08-04 14:27:30','My title'),(13,'some java at my place','2014-08-05 00:26:24','Coding ');
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `content` longtext,
  `posteAt` datetime DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `postedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9BDE863F388562DE` (`post_id`),
  CONSTRAINT `FK9BDE863F388562DE` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'Ngong','It is a cool programming languate','2014-08-01 05:29:15',1,'2014-08-07 07:16:17'),(2,'Peter','Sure holidays is a period of rest with one\'s family or so','2014-08-07 05:26:35',4,'2014-08-07 05:14:18'),(3,'Daisy','Your are right Ivoline, MVC is used by many frameworks out there',NULL,2,'2014-08-07 13:16:21'),(4,'Fongole','Yes young men , I know u did it well, that\'s cool',NULL,5,'2014-08-07 13:51:47'),(5,'Tambe','I like the holidays period. They enable us do what we want',NULL,4,'2014-08-07 16:32:07'),(6,'Tanwi','I think Holidays also give us the time to stay and have fellowship with our lord Jesus Christ. That\'s still some sort of enjoyment',NULL,4,'2014-08-07 17:05:19'),(7,'peter','pal',NULL,4,'2014-08-07 17:05:49'),(8,'suson','sdfqdf qsdfqsdf',NULL,4,'2014-08-07 17:06:06'),(9,'paul','Cool using captcha',NULL,5,'2014-08-07 17:11:28'),(10,'Derick','qsfd qdf D',NULL,6,'2014-08-12 18:25:21'),(11,'paul','sfqsf sqdfq',NULL,5,'2014-08-13 10:43:24'),(12,'peter','cool',NULL,7,'2014-08-14 16:57:24'),(13,'Elvis','It is still cool',NULL,1,'2014-09-12 19:16:36');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `posteAt` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `postedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK260CC0A7CD013E` (`author_id`),
  CONSTRAINT `FK260CC0A7CD013E` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'Java is a world wide known and appreciated language.More than 3 billion devices run java','2014-08-01 03:16:28','java',1,'2014-08-03 00:00:00'),(2,'MVC - Model View Controller is the architechture used by many frameworks like play, Codelgniter, laravel and others','2014-08-07 11:22:35','MVC',2,'2014-08-05 06:15:25'),(4,'Holidays is a period of rest or work , or both ?','2014-08-06 06:15:27','Hols',2,'2014-08-08 05:24:25'),(5,'Holidays is a period of rest , work or both','2014-08-06 05:18:33','Holidays',1,'2014-08-07 05:23:31'),(6,'Software developlment is the creation and rendering of softwares available to the public',NULL,'Developing',1,'2014-08-10 12:37:35'),(7,'Java is a world wide known and appreciated language.More than 3 billion devices run ',NULL,'java',1,'2014-08-10 13:10:19'),(8,'at Unity System          \r\n        ',NULL,'fx',3,'2014-08-12 18:26:10');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_tag`
--

DROP TABLE IF EXISTS `post_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_tag` (
  `Post_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL,
  PRIMARY KEY (`Post_id`,`tags_id`),
  KEY `FK30FE237B222C70F7` (`tags_id`),
  KEY `FK30FE237B388562DE` (`Post_id`),
  CONSTRAINT `FK30FE237B222C70F7` FOREIGN KEY (`tags_id`) REFERENCES `tag` (`id`),
  CONSTRAINT `FK30FE237B388562DE` FOREIGN KEY (`Post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_tag`
--

LOCK TABLES `post_tag` WRITE;
/*!40000 ALTER TABLE `post_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'development');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `isAdmin` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'tcheutchouasteve@gmail.com','Tcheutchoua Steve','','secret'),(2,'ivolingngong@gmail.com','Ivonling Ngnong Clarise','\0','cool'),(3,'martin@yahoo.com','Fonghoh Mrtin echelon','\0','martin'),(4,'tcsalist@yahoo.com','Tcsalist','','pass');
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

-- Dump completed on 2015-03-25 22:40:20
