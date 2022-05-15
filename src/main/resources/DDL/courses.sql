CREATE DATABASE  IF NOT EXISTS `courses_management`;
USE `courses_management`;

DROP TABLE IF EXISTS `students`;
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
  `professor` varchar(45) NOT NULL,
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `syllabus` varchar(100) DEFAULT NULL,
  `academic_year` varchar(45) DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  CONSTRAINT `professor` FOREIGN KEY (`professor`) REFERENCES `users` (`username`),
  PRIMARY KEY (`professor`, `id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- create the students table
--

CREATE TABLE `students` (
  `course_id` int NOT NULL,
  `id` int NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `registration_year` int DEFAULT NULL,
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  PRIMARY KEY (`course_id`, `id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



