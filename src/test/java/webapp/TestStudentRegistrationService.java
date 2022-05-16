package webapp;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import webapp.service.StudentRegistrationService;
import webapp.entity.StudentRegistration;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
public class TestStudentRegistrationService {
	
	@Autowired
	StudentRegistrationService StudentRegistrationService;
	
	@Test
	void testStudentRegistrationDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(StudentRegistrationService);
	}
	
	@Test
	void testFindByIdReturnsStudent() {
		StudentRegistration storedStudent = StudentRegistrationService.findById(1);
		Assertions.assertNotNull(storedStudent);
		Assertions.assertEquals("luke", storedStudent.getName());
	}
	
	@Test
	void testFindBycourseIDReturnsList() {
		List<StudentRegistration> storedStudents = StudentRegistrationService.findRegistrationsByCourseID(20);
		Assertions.assertNotNull(storedStudents);
		Assertions.assertEquals("[Student [id=1, student id=cs60001, course id=20, Name=luke, Semester=2, Registration Year=2016, exam grade=6.0, project grade=5.0, overall grade=55.0]]", storedStudents.toString());
	}
}
