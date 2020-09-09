/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.9-log : Database - chatt_application
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`chatt_application` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `chatt_application`;

/*Table structure for table `message_content_type` */

DROP TABLE IF EXISTS `message_content_type`;

CREATE TABLE `message_content_type` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `message_content_type` */

insert  into `message_content_type`(`id`,`code`,`name`,`created_date`,`modified_date`) values (1,'TEXT','Text','2020-04-13 19:47:56',NULL),(2,'MULTIMEDIA','Multimedia','2020-04-13 19:48:07',NULL);

/*Table structure for table `message_status` */

DROP TABLE IF EXISTS `message_status`;

CREATE TABLE `message_status` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `message_status` */

insert  into `message_status`(`id`,`code`,`name`,`created_date`) values (1,'SENT','Sent','2020-04-14 23:46:49'),(2,'READ','Read','2020-04-21 19:27:52'),(4,'UNREAD','Un Read','2020-04-21 19:28:40'),(5,'DELETED','Deleted','2020-04-21 19:29:04');

/*Table structure for table `messages` */

DROP TABLE IF EXISTS `messages`;

CREATE TABLE `messages` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT,
  `user_message_history_id` bigint(12) NOT NULL,
  `text_content` text,
  `multimedia_url` varchar(256) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `messages_FK_user_messages_history` (`user_message_history_id`),
  CONSTRAINT `messages_FK_user_messages_history` FOREIGN KEY (`user_message_history_id`) REFERENCES `user_message_history` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

/*Data for the table `messages` */

insert  into `messages`(`id`,`user_message_history_id`,`text_content`,`multimedia_url`,`created_date`) values (1,1,'hi',NULL,'2020-04-14 23:46:22'),(2,2,'hi',NULL,'2020-04-14 23:46:56'),(3,3,'Hellooo',NULL,'2020-04-14 23:49:50'),(4,4,'Ohh hi i was in meetting,\nso i could not reply you back',NULL,'2020-04-14 23:59:26'),(5,5,'I had some work with you.',NULL,'2020-04-15 00:03:16'),(6,6,'I had some work with you.',NULL,'2020-04-15 00:03:30'),(7,7,'sfffdffdfdfdfdf',NULL,'2020-04-15 00:05:32'),(8,8,'Hi\n',NULL,'2020-04-19 21:30:16'),(9,9,'Where Are you?',NULL,'2020-04-19 21:30:48'),(10,10,'I Am in flat?',NULL,'2020-04-19 21:36:39'),(11,11,'What did you wanted?',NULL,'2020-04-19 21:37:36'),(12,12,'Really\ndf\nffg\nfg\nfg\nfg\nfg\nfg\nfg\nfg\nfg\nfg',NULL,'2020-04-19 21:50:23'),(13,13,'gfjhfgfg\nfg\nfg\nfg\nfg\nfg\nfg\nfg\nfg\nfg\nfg\nf\ngf\ngfg\nfg\nfg\nfg\nfg\nfg\nfg\nfg\nfg\nfg\nfg\nfg\nfg\nfg\n',NULL,'2020-04-19 21:50:44'),(14,14,'dfdf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\n',NULL,'2020-04-19 21:57:24'),(15,15,'fdfdfdfdf',NULL,'2020-04-19 21:57:35'),(16,16,'dgdfdfdf',NULL,'2020-04-19 21:57:38'),(17,17,'dfdfd',NULL,'2020-04-19 21:57:39'),(18,18,'df\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\nd\nf',NULL,'2020-04-19 21:58:19'),(19,19,'sdsdsdsdsdssdsd\nsd\nsd\nsd\nsd\nsd\nsdsd\nsd\nsd\nsdks\ndsdks\ndlsd\nsdls\ndls\ndsd\nsldsddjfjfjsd\ns\nd\nsd\nsd\ns\nds\nd\nsdsds\n\n\n\n',NULL,'2020-04-19 22:03:07'),(20,20,'dfdfdfdf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\n',NULL,'2020-04-19 22:04:08'),(21,21,'\nere\nre\nre\nre\nre\nre\nr\ner\ner\ner\ner\ner\ner\ner\ner\ner\ner\ner\ner\ner\ner\ner\nrt\nrt\net\nrt\n45t\nt\nt\nrtr',NULL,'2020-04-19 22:05:18'),(22,22,'dfdf\nf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\nf\ndf\ndf\ndf\ndf\n',NULL,'2020-04-19 22:06:38'),(23,23,'sd\nsd\nsds\nds\nds\nds\nd\nsd\nsd',NULL,'2020-04-19 22:07:03'),(24,24,'sdsdsd\nsds\nds\nds\nds\nds\nd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\ns\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nd\nd\nd\nd\nd\nd\nd\nd\n',NULL,'2020-04-19 22:07:15'),(25,25,'ffdfdf',NULL,'2020-04-19 22:09:16'),(26,26,'dfdfdfdfdfdf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\nfddf',NULL,'2020-04-19 22:09:23'),(27,27,'sdsdsdsds\ns\n\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\ns\nds\nds\nd\nsd\nsd\nsd\nsd',NULL,'2020-04-19 22:09:41'),(28,28,'sdsdsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nd\nd\nd\nd\nd',NULL,'2020-04-19 22:16:53'),(29,29,'sd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\ns\nds\nds\nds\nd\nsdsd',NULL,'2020-04-19 22:17:03'),(30,30,'sd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\n',NULL,'2020-04-19 22:18:27'),(31,31,'dsdsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\n',NULL,'2020-04-19 22:18:35'),(32,32,'sdsd\ns\nds\nd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsdsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nssd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\n',NULL,'2020-04-19 22:21:06'),(33,33,'d\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\n',NULL,'2020-04-19 22:21:38'),(34,34,'sdbsdjsdsdjsdnsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\n\n\nsd\nsd\n\n\nsd\nsd\n',NULL,'2020-04-19 22:21:55'),(35,35,'dfd\nfdf\ndf\ndf\ndf\ndf\ndf\n',NULL,'2020-04-19 22:31:50'),(36,36,'sdjsdjsd\nsds\nd\nsds\nds\nds\ndsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd',NULL,'2020-04-19 22:32:02'),(37,37,'sd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\n',NULL,'2020-04-19 22:37:41'),(38,38,'sdsd\ns\ndsd',NULL,'2020-04-19 22:37:46'),(39,39,'sd\nsd\nsd\nsd\nsd\n',NULL,'2020-04-19 22:38:50'),(40,40,'sd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd',NULL,'2020-04-19 22:39:01'),(41,41,'sd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\ns\nds\nd',NULL,'2020-04-19 22:39:53'),(42,42,'cxcxcxcxcxc',NULL,'2020-04-19 22:39:57'),(43,43,'cxcxc\nxcxc\nxcxc\nxc',NULL,'2020-04-19 22:40:05'),(44,44,'xc\nc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc\nxc',NULL,'2020-04-19 22:40:16'),(45,45,'df\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ner\ner\ntf\nghg\nhghgh\ngh\ngh\ngh\ngh\ngh\ngh\ndd',NULL,'2020-04-19 22:41:36'),(46,46,'dsds\nd\nsd\nsd\nsd\nsd\nsd',NULL,'2020-04-19 22:44:42'),(47,47,'sdsds\nsd\nsd\nsds\nds',NULL,'2020-04-19 22:46:12'),(48,48,'aa\nsasasasas\ns\nas\nas\nas\nas\nas\nas\nas\nas\nas\nas\nas\nas\nas\nas\nas\nas\nas\nas\n',NULL,'2020-04-19 22:46:22'),(49,49,'sd\nsd\nsd\nsd\nsd\n',NULL,'2020-04-19 22:51:18'),(50,50,'sds\n',NULL,'2020-04-19 22:51:22'),(51,51,'sd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nsd\nee\nr\nrg\nrg\nrg\n\n\n\n\n\n\n\n\n\n\n\nggg\ng\ng\ng',NULL,'2020-04-19 22:51:31'),(52,52,'dfjdfjdfdf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndf\ndfkdfkdf\ndf\ndf',NULL,'2020-04-19 22:52:23'),(53,53,'sdddfdf',NULL,'2020-04-20 00:39:54'),(54,54,'sddddsdsdsd',NULL,'2020-04-20 00:40:00'),(55,55,'qasasas',NULL,'2020-04-20 00:40:38'),(56,56,'45454545',NULL,'2020-04-20 00:40:45'),(57,57,'565ff',NULL,'2020-04-20 00:42:19'),(58,58,'rtrtrt',NULL,'2020-04-20 00:43:06'),(59,59,'dfdfdf',NULL,'2020-04-20 00:46:08'),(60,60,'hyhyhyhy',NULL,'2020-04-20 00:46:13'),(61,61,'78787878',NULL,'2020-04-20 00:46:17'),(62,62,'ygfghhg',NULL,'2020-04-20 00:47:21'),(63,63,'66666',NULL,'2020-04-20 00:47:25'),(64,64,'',NULL,'2020-04-20 02:42:04'),(65,65,'fgfgfgttytyty',NULL,'2020-04-20 02:45:46'),(66,66,'',NULL,'2020-04-20 03:25:51'),(67,67,'fdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddf\ndf\ndfd\nf\ndf',NULL,'2020-04-20 03:26:53'),(68,68,'gthgthyhyh',NULL,'2020-04-22 19:45:14'),(69,69,'hi',NULL,'2020-04-23 02:05:41'),(70,70,'hi',NULL,'2020-04-23 02:06:11'),(71,71,'hi',NULL,'2020-04-23 02:08:18'),(72,72,'hiiiii',NULL,'2020-04-23 02:11:38'),(73,73,'54545455454',NULL,'2020-04-23 02:15:35'),(74,74,'gffggtthth',NULL,'2020-04-23 02:15:41'),(75,75,'errt56tgttfgfg',NULL,'2020-04-23 02:15:54'),(76,76,'fhghhy6767hghgu6thghthgh',NULL,'2020-04-23 02:16:04'),(77,77,'hi',NULL,'2020-04-26 17:23:30'),(78,78,'how are you',NULL,'2020-05-04 00:00:38'),(79,79,'i am fine',NULL,'2020-05-04 00:00:57'),(80,80,'abcd',NULL,'2020-05-04 00:18:18'),(81,81,'dgffgfgfg',NULL,'2020-05-04 00:18:21'),(82,82,'tythtythghgh',NULL,'2020-05-04 00:18:24'),(83,83,'hi shahbaj',NULL,'2020-05-07 20:21:07'),(84,84,'Where are you?',NULL,'2020-05-07 20:23:55'),(85,85,'I was in meeting',NULL,'2020-05-07 20:24:04'),(86,86,'sfdfdfdf',NULL,'2020-05-07 20:24:58'),(87,87,'dfdfdfdf',NULL,'2020-05-07 20:25:01');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(256) NOT NULL,
  `middle_name` varchar(256) DEFAULT NULL,
  `last_name` varchar(256) DEFAULT NULL,
  `mobile_num` bigint(15) NOT NULL,
  `email_id` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `session_id` varchar(256) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`first_name`,`middle_name`,`last_name`,`mobile_num`,`email_id`,`password`,`session_id`,`created_date`,`modified_date`) values (1,'sumit',NULL,'Dhumne',9545973203,'sumitdhumne@gmail.com','sam123','a96293ad-0b98-4f17-a9c0-48a2eadb23e7','2020-04-14 19:27:12',NULL),(2,'Shahbaj',NULL,'Bagwan',9665647423,'shahbajbagwan@gmail.com','shahbaj123','0d0d372e-2680-4dec-b825-d786faead3a3','2020-04-14 20:55:55',NULL);

/*Table structure for table `user_message_history` */

DROP TABLE IF EXISTS `user_message_history`;

CREATE TABLE `user_message_history` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT,
  `sender_user_id` int(10) NOT NULL,
  `receiver_user_id` int(10) NOT NULL,
  `message_type` int(3) NOT NULL,
  `status` int(2) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_message_history_FK_message_content_type` (`message_type`),
  KEY `user_message_history_sender_FK_user` (`sender_user_id`),
  KEY `user_message_history_reciver_FK_user` (`receiver_user_id`),
  KEY `user_message_history_FK_message_status` (`status`),
  CONSTRAINT `user_message_history_FK_message_content_type` FOREIGN KEY (`message_type`) REFERENCES `message_content_type` (`id`),
  CONSTRAINT `user_message_history_FK_message_status` FOREIGN KEY (`status`) REFERENCES `message_status` (`id`),
  CONSTRAINT `user_message_history_reciver_FK_user` FOREIGN KEY (`receiver_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_message_history_sender_FK_user` FOREIGN KEY (`sender_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

/*Data for the table `user_message_history` */

insert  into `user_message_history`(`id`,`sender_user_id`,`receiver_user_id`,`message_type`,`status`,`created_date`,`modified_date`) values (1,1,2,1,2,'2020-04-14 23:46:22','2020-04-26 17:22:52'),(2,1,2,1,2,'2020-04-14 23:46:56','2020-04-26 17:22:53'),(3,1,2,1,2,'2020-04-14 23:49:50','2020-04-26 17:22:52'),(4,2,1,1,2,'2020-04-14 23:59:26','2020-04-26 17:23:19'),(5,1,2,1,2,'2020-04-15 00:03:16','2020-04-26 17:22:52'),(6,1,2,1,2,'2020-04-15 00:03:30','2020-04-26 17:22:52'),(7,2,1,1,2,'2020-04-15 00:05:32','2020-04-26 17:23:19'),(8,1,2,1,2,'2020-04-19 21:30:16','2020-04-26 17:22:52'),(9,1,2,1,2,'2020-04-19 21:30:48','2020-04-26 17:22:52'),(10,2,1,1,2,'2020-04-19 21:36:39','2020-04-26 17:23:20'),(11,1,2,1,2,'2020-04-19 21:37:36','2020-04-26 17:22:51'),(12,2,1,1,2,'2020-04-19 21:50:23','2020-04-26 17:23:19'),(13,2,1,1,2,'2020-04-19 21:50:44','2020-04-26 17:23:18'),(14,2,1,1,2,'2020-04-19 21:57:24','2020-04-26 17:23:17'),(15,2,1,1,2,'2020-04-19 21:57:35','2020-04-26 17:23:17'),(16,2,1,1,2,'2020-04-19 21:57:38','2020-04-26 17:23:17'),(17,2,1,1,2,'2020-04-19 21:57:39','2020-04-26 17:23:17'),(18,2,1,1,2,'2020-04-19 21:58:19','2020-04-26 17:23:17'),(19,2,1,1,2,'2020-04-19 22:03:07','2020-04-26 17:23:17'),(20,2,1,1,2,'2020-04-19 22:04:08','2020-04-26 17:23:17'),(21,2,1,1,2,'2020-04-19 22:05:18','2020-04-26 17:23:16'),(22,2,1,1,2,'2020-04-19 22:06:38','2020-04-26 17:22:49'),(23,2,1,1,2,'2020-04-19 22:07:03','2020-04-26 17:22:48'),(24,2,1,1,2,'2020-04-19 22:07:15','2020-04-26 17:22:49'),(25,2,1,1,2,'2020-04-19 22:09:16','2020-04-26 17:22:49'),(26,2,1,1,2,'2020-04-19 22:09:23','2020-04-26 17:22:48'),(27,2,1,1,2,'2020-04-19 22:09:41','2020-04-26 17:22:47'),(28,2,1,1,2,'2020-04-19 22:16:53','2020-04-26 17:22:47'),(29,2,1,1,2,'2020-04-19 22:17:03','2020-04-26 17:22:47'),(30,2,1,1,2,'2020-04-19 22:18:27','2020-04-26 17:22:47'),(31,2,1,1,2,'2020-04-19 22:18:35','2020-04-26 17:22:47'),(32,2,1,1,2,'2020-04-19 22:21:05','2020-04-26 17:22:46'),(33,2,1,1,2,'2020-04-19 22:21:38','2020-04-26 17:22:46'),(34,2,1,1,2,'2020-04-19 22:21:55','2020-04-26 17:22:46'),(35,2,1,1,2,'2020-04-19 22:31:50','2020-04-26 17:22:45'),(36,2,1,1,2,'2020-04-19 22:32:02','2020-04-26 17:22:45'),(37,2,1,1,2,'2020-04-19 22:37:41','2020-04-26 17:22:44'),(38,2,1,1,2,'2020-04-19 22:37:46','2020-04-26 17:22:44'),(39,2,1,1,2,'2020-04-19 22:38:50','2020-04-26 17:22:44'),(40,2,1,1,2,'2020-04-19 22:39:01','2020-04-26 17:22:43'),(41,2,1,1,2,'2020-04-19 22:39:53','2020-04-26 17:22:43'),(42,2,1,1,2,'2020-04-19 22:39:57','2020-04-26 17:22:42'),(43,2,1,1,2,'2020-04-19 22:40:05','2020-04-26 17:22:42'),(44,2,1,1,2,'2020-04-19 22:40:16','2020-04-26 17:22:42'),(45,2,1,1,2,'2020-04-19 22:41:36','2020-04-26 17:22:42'),(46,2,1,1,2,'2020-04-19 22:44:42','2020-04-26 17:22:42'),(47,2,1,1,2,'2020-04-19 22:46:12','2020-04-26 17:22:41'),(48,2,1,1,2,'2020-04-19 22:46:22','2020-04-26 17:22:40'),(49,2,1,1,2,'2020-04-19 22:51:18','2020-04-26 17:22:40'),(50,2,1,1,2,'2020-04-19 22:51:22','2020-04-26 17:22:40'),(51,2,1,1,2,'2020-04-19 22:51:31','2020-04-26 17:22:40'),(52,2,1,1,2,'2020-04-19 22:52:23','2020-04-26 17:22:39'),(53,2,1,1,2,'2020-04-20 00:39:54','2020-04-26 17:22:38'),(54,2,1,1,2,'2020-04-20 00:40:00','2020-04-26 17:22:38'),(55,2,1,1,2,'2020-04-20 00:40:38','2020-04-26 17:22:38'),(56,2,1,1,2,'2020-04-20 00:40:45','2020-04-26 17:22:38'),(57,2,1,1,2,'2020-04-20 00:42:19','2020-04-26 17:22:38'),(58,2,1,1,2,'2020-04-20 00:43:06','2020-04-26 17:22:38'),(59,2,1,1,2,'2020-04-20 00:46:08','2020-04-26 17:22:38'),(60,2,1,1,2,'2020-04-20 00:46:13','2020-04-26 17:22:38'),(61,2,1,1,2,'2020-04-20 00:46:16','2020-04-26 17:22:37'),(62,2,1,1,2,'2020-04-20 00:47:21','2020-04-26 17:22:37'),(63,2,1,1,2,'2020-04-20 00:47:25','2020-04-26 17:22:37'),(64,2,1,1,2,'2020-04-20 02:42:04','2020-04-26 17:22:37'),(65,2,1,1,2,'2020-04-20 02:45:46','2020-04-26 17:22:37'),(66,2,1,1,2,'2020-04-20 03:25:51','2020-04-26 17:22:11'),(67,2,1,1,2,'2020-04-20 03:26:53','2020-04-26 17:22:11'),(68,2,1,1,2,'2020-04-22 19:45:14','2020-04-26 17:22:11'),(69,1,2,1,2,'2020-04-23 02:05:41','2020-04-26 17:22:15'),(70,1,2,1,2,'2020-04-23 02:06:11','2020-04-26 17:22:16'),(71,1,2,1,2,'2020-04-23 02:08:18','2020-04-26 17:22:16'),(72,1,2,1,2,'2020-04-23 02:11:38','2020-04-26 17:22:16'),(73,1,2,1,2,'2020-04-23 02:15:35','2020-04-26 17:22:16'),(74,1,2,1,2,'2020-04-23 02:15:41','2020-04-26 17:22:16'),(75,1,2,1,2,'2020-04-23 02:15:54','2020-04-26 17:22:16'),(76,1,2,1,2,'2020-04-23 02:16:04','2020-04-26 17:22:17'),(77,2,1,1,2,'2020-04-26 17:23:30','2020-04-26 17:23:30'),(78,2,1,1,2,'2020-05-04 00:00:38','2020-05-04 00:00:43'),(79,2,1,1,2,'2020-05-04 00:00:57','2020-05-04 00:01:00'),(80,1,2,1,2,'2020-05-04 00:18:18','2020-05-04 00:18:32'),(81,1,2,1,2,'2020-05-04 00:18:21','2020-05-04 00:18:32'),(82,1,2,1,2,'2020-05-04 00:18:24','2020-05-04 00:18:33'),(83,1,2,1,2,'2020-05-07 20:21:06','2020-05-07 20:23:32'),(84,2,1,1,2,'2020-05-07 20:23:55','2020-05-07 20:24:06'),(85,2,1,1,2,'2020-05-07 20:24:04','2020-05-07 20:24:13'),(86,1,2,1,2,'2020-05-07 20:24:58','2020-05-07 20:25:02'),(87,1,2,1,2,'2020-05-07 20:25:01','2020-05-07 20:25:06');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
