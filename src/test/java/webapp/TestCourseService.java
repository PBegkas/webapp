package webapp;


import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import webapp.entity.Course;
import webapp.service.CourseService;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class TestCourseService {
	
	@Autowired
	CourseService CourseService;
	
	@Test
	void testCourseDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(CourseService);
	}
	
	@Test
	void testFindByIdReturnsCourse() {
		Course storedCourse = CourseService.findById(20);
		Assertions.assertNotNull(storedCourse);
		Assertions.assertEquals("python", storedCourse.getName());
	}
	
	@Test
	void testfindCourseByInstructorLoginReturnsList() {
		List<Course> storedCourses = CourseService.findCourseByInstructorLogin("zarras");
		Assertions.assertNotNull(storedCourses);
		Assertions.assertEquals("[Course [id=20, Course id= myy001, Professor=zarras, Name=python, Syllabus=midterms, Academic year=2020-2021, Semester=1, Description=learn basic python], Course [id=21, Course id= myyy666, Professor=zarras, Name=nextd, Syllabus=wert, Academic year=2, Semester=4, Description=asdfsdfsdfsdf]]", storedCourses.toString());
	}
}
