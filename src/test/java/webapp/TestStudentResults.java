package webapp;

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
public class TestStudentResults {
	
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
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}
	
	

	
	@WithMockUser(value = "zarras")
	@Test 
	void test2SaveGradesStudentPage() throws Exception {
		
		int theCourseId = 1;
		int theStudentId = 1;
		
		StudentRegistration student = studentsService.findById(theStudentId);
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("id", Integer.toString(student.getId()));
	    multiValueMap.add("studentId", student.getStudentId());
	    multiValueMap.add("courseId", Integer.toString(student.getCourseID()));
	    multiValueMap.add("name", student.getName());
	    multiValueMap.add("Registration_Year", Integer.toString(student.getRegistration_Year()));
	    multiValueMap.add("Semester", Integer.toString(student.getSemester()));
	    multiValueMap.add("projectGrade", "10");
	    multiValueMap.add("examGrade", "5");
		
		
		mockMvc.perform(
				post("/students/saveGrades")
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/students/list?courseId="+theCourseId));	
		Assertions.assertEquals("[Student [id=1, student id=cs60001, course id=1, Name=luke, Semester=2, "
				+ "Registration Year=2016, exam grade=5.0, project grade=10.0, overall grade=-1.0]]", 
				studentsService.findRegistrationsByCourseID(theCourseId).toString());

	}

	
	@WithMockUser(value = "zarras")
	@Test 
	void testWOverallGradePage() throws Exception {
		
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("projectWeight", "0.5");
		multiValueMap.add("projectBase", "5");
		multiValueMap.add("examWeight", "0.5");
		multiValueMap.add("examBase", "5");
		
		int theCourseId = 1;
		
		mockMvc.perform(
				post("/students/saveParam?courseId=" + theCourseId)
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/students/list?courseId=" + theCourseId));	
		Assertions.assertEquals("[Student [id=1, student id=cs60001, course id=1, Name=luke, Semester=2, "
				+ "Registration Year=2016, exam grade=5.0, project grade=10.0, overall grade=7.5]]", 
				studentsService.findRegistrationsByCourseID(theCourseId).toString());

	}
}
