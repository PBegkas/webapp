package webapp;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import webapp.dao.*;
import webapp.entity.StudentRegistration;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
public class TestStudentRegistrationDAO {
	
	@Autowired
	StudentRegistrationDAO StudentRegistrationDAO;
	
	@Test
	void testStudentRegistrationDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(StudentRegistrationDAO);
	}
	
	@Test
	void testFindByIdReturnsStudent() {
		StudentRegistration storedStudent = StudentRegistrationDAO.findById(1);
		Assertions.assertNotNull(storedStudent);
		Assertions.assertEquals("luke", storedStudent.getName());
	}
	
	@Test
	void testFindBycourseIDReturnsList() {
		List<StudentRegistration> storedStudents = StudentRegistrationDAO.findByCourseId(1);
		Assertions.assertNotNull(storedStudents);
		Assertions.assertEquals("[Student [id=1, student id=cs60001, course id=1, Name=luke, Semester=2, Registration Year=2016, exam grade=-1.0, project grade=-1.0, overall grade=-1.0]]", storedStudents.toString());
	}
}
