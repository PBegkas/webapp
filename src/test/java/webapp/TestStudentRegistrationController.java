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
	void testListStudentsReturnsPage() throws Exception {
		
		int theCourseId = 1;
		int theStudentId = 2;
		
		mockMvc.perform(get("/students/list?courseId="+theCourseId)).
		andExpect(status().isOk()).
		andExpect(view().name("students/list-students"));		
	}

	@WithMockUser(value = "zarras")
	@Test 
	void testSaveStudentReturnsPage() throws Exception {
		
		int theCourseId = 1;
		int theStudentId = 2;
		
		// ID and courseId dont matter
		StudentRegistration student = new StudentRegistration("cs00000", "Test student's name", 1, 2000);
	    	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("studentId", student.getStudentId());
	    multiValueMap.add("name", student.getName());
	    multiValueMap.add("Registration_Year", Integer.toString(student.getRegistration_Year()));
	    multiValueMap.add("Semester", Integer.toString(student.getSemester()));
		mockMvc.perform(
				post("/students/save")
				//.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/students/list?courseId="+theCourseId));	
		
		Assertions.assertEquals("hi", 
				studentsService.findRegistrationsByCourseID(theCourseId).toString());
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void testUpdateStudentReturnsPage() throws Exception {
		
		int theCourseId = 1;
		int theStudentId = 2;
		
		StudentRegistration student = new StudentRegistration(theStudentId, "cs00000", theCourseId, "Test student's UPDATED name", 1, 2000);
	    	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("id", Integer.toString(student.getId()));
	    multiValueMap.add("courseId", Integer.toString(student.getCourseID()));
	    multiValueMap.add("name", student.getName());
	    multiValueMap.add("Registration_Year", Integer.toString(student.getRegistration_Year()));
	    multiValueMap.add("Semester", Integer.toString(student.getSemester()));
		mockMvc.perform(
				post("/students/save")
				//.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/students/list?courseId="+theCourseId));	
		
		Assertions.assertEquals("hi", 
				studentsService.findRegistrationsByCourseID(theCourseId).toString());
	}
	
	
	@WithMockUser(value = "zarras")
	@Test 
	void testDeleteStudentReturnsPage() throws Exception {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		
		int theCourseId = 1;
		int theStudentId = 2;
		
		mockMvc.perform(
				post("/students/delete?studentId="+theStudentId)
				//.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/students/list?courseId="+theCourseId));	
		Assertions.assertEquals("hi", studentsService.findRegistrationsByCourseID(theCourseId).toString());

	}

}
