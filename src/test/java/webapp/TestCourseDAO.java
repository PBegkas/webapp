package webapp;


import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import webapp.dao.*;
import webapp.entity.Course;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class TestCourseDAO {
	
	@Autowired
	CourseDAO CourseDAO;
	
	@Test
	void testCourseDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(CourseDAO);
	}
	
	@Test
	void testFindByIdReturnsCourse() {
		Course storedCourse = CourseDAO.findById(1);
		Assertions.assertNotNull(storedCourse);
		Assertions.assertEquals("python", storedCourse.getName());
	}
	
	@Test
	void testFindByProfessorReturnsList() {
		List<Course> storedCourses = CourseDAO.findByProfessor("zarras");
		Assertions.assertNotNull(storedCourses);
		Assertions.assertEquals("[Course [id=1, Course id= myy001, Professor=zarras, Name=python, Syllabus=midterms, "
				+ "Academic year=2020-2021, Semester=1, Description=learn basic python]]", storedCourses.toString());
	}
}
