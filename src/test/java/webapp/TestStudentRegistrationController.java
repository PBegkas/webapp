package webapp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import webapp.controller.webappStudentsController;
import webapp.entity.StudentRegistration;
import webapp.service.StudentRegistrationService;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
@AutoConfigureMockMvc
class TestStudentController {
	
	@Autowired
	StudentRegistrationService studentsService;
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	webappStudentsController studentsController;

	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }
	
	@Test
	void testStudentControllerIsNotNull() {
		Assertions.assertNotNull(studentsController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}
	
	
	@WithMockUser(value = "zarras")
	@Test 
	void test1ListStudentsReturnsPage() throws Exception {
		
		int theCourseId = 1;
		int theStudentId = 2;
		
		mockMvc.perform(get("/students/list?courseId="+theCourseId)).
		andExpect(status().isOk()).
		andExpect(view().name("students/list-students"));		
	}

	@WithMockUser(value = "zarras")
	@Test 
	void test2SaveStudentReturnsPage() throws Exception {
		int theCourseId = 1;
		
		// ID and courseId dont matter
		StudentRegistration student = new StudentRegistration("cs00000", "Test student's name", 1, 2000);
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("studentId", student.getStudentId());
	    multiValueMap.add("name", student.getName());
	    multiValueMap.add("Registration_Year", Integer.toString(student.getRegistration_Year()));
	    multiValueMap.add("Semester", Integer.toString(student.getSemester()));
		mockMvc.perform(
				post("/students/save?courseId="+theCourseId)
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/students/list?courseId="+theCourseId));	
		
		Assertions.assertEquals("[Student [id=1, student id=cs60001, course id=1, Name=luke, Semester=2, "
				+ "Registration Year=2016, exam grade=-1.0, project grade=-1.0, overall grade=-1.0], "
				+ "Student [id=2, student id=cs00000, course id=1, Name=Test student's name, Semester=1, "
				+ "Registration Year=2000, exam grade=-1.0, project grade=-1.0, overall grade=-1.0]]", 
				studentsService.findRegistrationsByCourseID(theCourseId).toString());
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void test3UpdateStudentReturnsPage() throws Exception {
		int theCourseId = 1;
		int theStudentId = 2;
		
		StudentRegistration student = new StudentRegistration(theStudentId, "cs00000", theCourseId, "Test student's UPDATED name", 1, 2000);
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("id", Integer.toString(student.getId()));
	    multiValueMap.add("studentId", student.getStudentId());
	    multiValueMap.add("courseId", Integer.toString(student.getCourseID()));
	    multiValueMap.add("name", student.getName());
	    multiValueMap.add("Registration_Year", Integer.toString(student.getRegistration_Year()));
	    multiValueMap.add("Semester", Integer.toString(student.getSemester()));
		mockMvc.perform(
				post("/students/save?courseId="+theCourseId)
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/students/list?courseId="+theCourseId));	
		
		Assertions.assertEquals("[Student [id=1, student id=cs60001, course id=1, Name=luke, Semester=2, "
				+ "Registration Year=2016, exam grade=-1.0, project grade=-1.0, overall grade=-1.0], "
				+ "Student [id=2, student id=cs00000, course id=1, Name=Test student's UPDATED name, Semester=1, "
				+ "Registration Year=2000, exam grade=-1.0, project grade=-1.0, overall grade=-1.0]]", 
				studentsService.findRegistrationsByCourseID(theCourseId).toString());
	}
	
	
	@WithMockUser(value = "zarras")
	@Test 
	void testWDeleteStudentReturnsPage() throws Exception {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		int theCourseId = 1;
		int theStudentId = 2;
		
		mockMvc.perform(
				post("/students/delete?studentId="+theStudentId)
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/students/list?courseId="+theCourseId));	
		Assertions.assertEquals("[Student [id=1, student id=cs60001, course id=1, Name=luke, Semester=2, "
				+ "Registration Year=2016, exam grade=-1.0, project grade=-1.0, overall grade=-1.0]]", 
				studentsService.findRegistrationsByCourseID(theCourseId).toString());

	}
	

}
