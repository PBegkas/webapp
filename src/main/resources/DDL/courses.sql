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
  `id` int NOT NULL auto_increment,
  `course_id` varchar(50) NOT NULL,
  `professor` varchar(50) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `syllabus` varchar(100) DEFAULT NULL,
  `academic_year` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `semester` int DEFAULT NULL,
  PRIMARY KEY (`id`,`professor`),
  KEY `professor_idx` (`professor`),
  CONSTRAINT `professor` FOREIGN KEY (`professor`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


insert into courses(id,course_id,professor,name,syllabus,academic_year,description,semester)values(1,'myy001','zarras','python','midterms','2020-2021','learn basic python',1);

--
-- create the students table
--

CREATE TABLE `students` (
  `id` int NOT NULL auto_increment,
  `student_id` varchar(45) DEFAULT NULL,
  `course_id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `registration_year` int DEFAULT NULL,
  `exam_grade` decimal(10,2) DEFAULT -1,
  `project_grade` decimal(10,2) DEFAULT -1,
  `overall_grade` decimal(10,2) DEFAULT -1,
  PRIMARY KEY (`id`,`course_id`),
  KEY `course_id_idx` (`course_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

insert into students(id,student_id,course_id,name,semester,registration_year,exam_grade,project_grade,overall_grade)values(1,'cs60001',1,'luke',2,2016,-1,-1,-1)



