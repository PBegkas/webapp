CREATE DATABASE  IF NOT EXISTS `courses_management`;
USE `courses_management`;


DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `enrolled`;
DROP TABLE IF EXISTS `teaches`;
DROP TABLE IF EXISTS `professors`;
DROP TABLE IF EXISTS `courses`;
DROP TABLE IF EXISTS `students`;

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

CREATE TABLE `enrolled` (
  `course_id` int NOT NULL,
  `student_id` int NOT NULL,
  `exam_grade` DECIMAL,
  `project_grade` DECIMAL,
  PRIMARY KEY (`course_id`,`student_id`),
  KEY `student_id_idx` (`student_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `student_id` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- create the teaches table
--

CREATE TABLE `teaches` (
  `professor` varchar(50) NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`professor`,`course_id`),
  KEY `course_id_idx` (`course_id`),
  CONSTRAINT `course` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `professor` FOREIGN KEY (`professor`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

