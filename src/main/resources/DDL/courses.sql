CREATE DATABASE  IF NOT EXISTS `courses_management`;
USE `courses_management`;


DROP TABLE IF EXISTS `enrolled`;
DROP TABLE IF EXISTS `teaches`;
DROP TABLE IF EXISTS `students`;
DROP TABLE IF EXISTS `professors`;
DROP TABLE IF EXISTS `courses`;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;



--
-- create the users table
--

create table `users` (
	username varchar(50) not null,
    password varchar(120) not null,
    enabled boolean not null,
	PRIMARY KEY (`username`)
);

create table `authorities` (
    username varchar(50) not null,
    authority varchar(50) not null,
    foreign key (username) references users (username)
);


insert into users(username, password, enabled)values('zarras','{noop}zarras',true);
insert into users(username, password, enabled)values('pvassil','{noop}pvassil',true);
 
insert into authorities(username,authority)values('zarras','ROLE_ADMIN');
insert into authorities(username,authority)values('zarras','ROLE_USER');
insert into authorities(username,authority)values('pvassil','ROLE_USER');

--
-- create the courses table
--

CREATE TABLE `courses` (
  `id` int NOT NULL,
  `professor` varchar(50) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `syllabus` varchar(100) DEFAULT NULL,
  `academic_year` int DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `semester` int DEFAULT NULL,
  PRIMARY KEY (`id`,`professor`),
  KEY `professor_idx` (`professor`),
  CONSTRAINT `professor` FOREIGN KEY (`professor`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- create the students table
--

CREATE TABLE `students` (
  `id` int NOT NULL,
  `course_id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `registration_year` int DEFAULT NULL,
  `exam_grade` decimal(10,0) DEFAULT NULL,
  `project_grade` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`,`course_id`),
  KEY `course_id_idx` (`course_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



