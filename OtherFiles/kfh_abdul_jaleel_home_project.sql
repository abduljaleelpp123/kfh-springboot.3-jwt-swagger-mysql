/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.28-MariaDB : Database - kfh_abdul_home_project
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kfh_abdul_home_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `kfh_abdul_home_project`;

/*Table structure for table `abdul_course` */

DROP TABLE IF EXISTS `abdul_course`;

CREATE TABLE `abdul_course` (
  `course_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_code` varchar(5) DEFAULT NULL,
  `course_name` varchar(25) DEFAULT NULL,
  `course_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `abdul_course` */

insert  into `abdul_course`(`course_id`,`course_code`,`course_name`,`course_status`) values 
(1,'BA-E','BA-English',1),
(2,'BA-AR','BA-Arabic',1),
(3,'BSBIO','BSc Biology',1);

/*Table structure for table `abdul_course_student` */

DROP TABLE IF EXISTS `abdul_course_student`;

CREATE TABLE `abdul_course_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_id` bigint(20) NOT NULL,
  `st_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK579tl94l3r73x9rkddmmde3a4` (`course_id`),
  KEY `FKpcvxh7uo3ob9gc73pyeagtt8l` (`st_id`),
  CONSTRAINT `FK579tl94l3r73x9rkddmmde3a4` FOREIGN KEY (`course_id`) REFERENCES `abdul_course` (`course_id`),
  CONSTRAINT `FKpcvxh7uo3ob9gc73pyeagtt8l` FOREIGN KEY (`st_id`) REFERENCES `abdul_student` (`st_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `abdul_course_student` */

/*Table structure for table `abdul_roles` */

DROP TABLE IF EXISTS `abdul_roles`;

CREATE TABLE `abdul_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` enum('ROLE_ADMIN','ROLE_USER') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `abdul_roles` */

insert  into `abdul_roles`(`id`,`name`) values 
(1,'ROLE_ADMIN'),
(2,'ROLE_USER');

/*Table structure for table `abdul_student` */

DROP TABLE IF EXISTS `abdul_student`;

CREATE TABLE `abdul_student` (
  `st_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stAr_name` varchar(90) DEFAULT NULL,
  `stEg_name` varchar(90) DEFAULT NULL,
  `st_email` varchar(90) DEFAULT NULL,
  `st_status` int(11) DEFAULT NULL,
  `st_address` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`st_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `abdul_student` */

insert  into `abdul_student`(`st_id`,`stAr_name`,`stEg_name`,`st_email`,`st_status`,`st_address`) values 
(1,'عبد الجليل','Abdul jaleel','abduljaleel@yahoo.com',1,'Palamadathil house');

/*Table structure for table `abdul_user_roles` */

DROP TABLE IF EXISTS `abdul_user_roles`;

CREATE TABLE `abdul_user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK7qrfxoym1qpcc7wyjw2tbjq9y` (`role_id`),
  CONSTRAINT `FK71tk7vk2dpocfmfrci1t4fi3g` FOREIGN KEY (`user_id`) REFERENCES `abdul_users` (`id`),
  CONSTRAINT `FK7qrfxoym1qpcc7wyjw2tbjq9y` FOREIGN KEY (`role_id`) REFERENCES `abdul_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `abdul_user_roles` */

insert  into `abdul_user_roles`(`user_id`,`role_id`) values 
(1,1);

/*Table structure for table `abdul_users` */

DROP TABLE IF EXISTS `abdul_users`;

CREATE TABLE `abdul_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gender` varchar(20) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `password` varchar(5120) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKcg4vpbnefm2wtrrfu1xx4q561` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `abdul_users` */

insert  into `abdul_users`(`id`,`gender`,`name`,`password`,`status`,`username`) values 
(1,'male','AbdulJaleel','$2a$10$FKCfVmHdj8VpSLWBRCmLAOTs.Lul4BYcVH1zzVMuBCLl.k7.LMuyW','active','abduljaleel123@gmail.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
