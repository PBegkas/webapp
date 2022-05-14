CREATE DATABASE  IF NOT EXISTS `courses management`;
USE `courses management`;


--
-- create the users table
--

DROP TABLE IF EXISTS `professors`;

create table `professors` (
	username varchar(50) not null,
    password varchar(120) not null,
	PRIMARY KEY (`username`)
);


--
-- create the courses table
--

DROP TABLE IF EXISTS `courses`;

CREATE TABLE `courses` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `syllabus` varchar(100) DEFAULT NULL,
  `academic_year` varchar(45) DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- create the students table
--

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
  `id` int NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `registration_year` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- create the enrolled table
--

DROP TABLE IF EXISTS `enrolled`;

CREATE TABLE `enrolled` (
  `course_id` int NOT NULL,
  `student_id` int NOT NULL,
  PRIMARY KEY (`course_id`,`student_id`),
  KEY `student_id_idx` (`student_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `student_id` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- create the teaches table
--

DROP TABLE IF EXISTS `teaches`;

CREATE TABLE `teaches` (
  `professor` varchar(50) NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`professor`,`course_id`),
  KEY `course_id_idx` (`course_id`),
  CONSTRAINT `course` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `professor` FOREIGN KEY (`professor`) REFERENCES `professors` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
