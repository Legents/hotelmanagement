-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hotelmanagement
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `history` (
  `bookId` varchar(20) NOT NULL,
  `arrive` date DEFAULT NULL,
  `depart` date DEFAULT NULL,
  `money` decimal(10,2) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `roomId` varchar(20) DEFAULT NULL,
  `phone` varchar(13) DEFAULT NULL,
  `waiter` varchar(10) DEFAULT NULL,
  `checkoutwaiter` varchar(10) DEFAULT NULL,
  `read` tinyint(4) DEFAULT '0' COMMENT '0  未读\n1  已读',
  PRIMARY KEY (`bookId`),
  KEY `phone` (`phone`),
  KEY `waiter` (`waiter`),
  KEY `history_ibfk_4_idx` (`checkoutwaiter`),
  CONSTRAINT `history_ibfk_2` FOREIGN KEY (`phone`) REFERENCES `user` (`phone`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `history_ibfk_3` FOREIGN KEY (`waiter`) REFERENCES `waiter` (`account`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `history_ibfk_4` FOREIGN KEY (`checkoutwaiter`) REFERENCES `waiter` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES ('00002','2019-07-02','2019-07-05',444.00,'正常入住','0201','11111111110','2222222220',NULL,0),('12','2019-07-05','2019-07-06',NULL,'房间不足，预定失败',NULL,'11111111110','2222222220',NULL,0),('13','2019-07-07','2019-07-07',NULL,'房间不足，预定失败',NULL,'11111111110','2222222220',NULL,0),('20','2019-08-08','2019-08-09',58.00,'正常入住','0201 0203','11111111110','2222222220','2222222220',0),('7','2019-07-05','2019-07-07',888.00,'正常入住',' 0201 0202','11111111110','2222222220','2222222220',0),('8','2019-07-07','2019-07-07',0.00,'用户退款',NULL,'11111111110',NULL,NULL,0);
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-12 13:54:41
