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

import webapp.controller.webappCoursesController;
import webapp.entity.Course;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
@AutoConfigureMockMvc
class TestCourseController {
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	webappCoursesController coursesController;

	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }
	
	@Test
	void testCoursesControllerIsNotNull() {
		Assertions.assertNotNull(coursesController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}
	
	
	@WithMockUser(value = "zarras")
	@Test 
	void testListCoursesReturnsPage() throws Exception {
		mockMvc.perform(get("/courses/list")).
		andExpect(status().isOk()).
		andExpect(view().name("courses/list-courses"));		
	}

	@WithMockUser(value = "zarras")
	@Test 
	void testSaveCourseReturnsPage() throws Exception {
		
		// ID and professor dont matter
		Course course = new Course(21, "myy000","zarras" ,"Test course" ,"This is a test course's syllabus", "2000-2001", 1, "Test course's description");
	    	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    //multiValueMap.add("id", Integer.toString(course.getId()));
	    multiValueMap.add("courseId", course.getCourseId());
	    multiValueMap.add("name", course.getName());
	    multiValueMap.add("Academic_year", course.getAcademic_year());
	    multiValueMap.add("Semester", Integer.toString(course.getSemester()));
	    multiValueMap.add("Description", course.getDescription());
	    multiValueMap.add("Syllabus", course.getSyllabus());
	    //multiValueMap.add("professor", course.getProfessor());
		mockMvc.perform(
				post("/courses/save")
				//.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/courses/list"));	
	}
	
	
	@WithMockUser(value = "zarras")
	@Test 
	void testDeleteCourseReturnsPage() throws Exception {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		/*
		Course course = new Course(22, "myy000","zarras" ,"Test course" ,"This is a test course's syllabus", "2000-2001", 1, "Test course's description");
	    	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("id", Integer.toString(course.getId()));
	    multiValueMap.add("courseId", course.getCourseId());
	    multiValueMap.add("name", course.getName());
	    multiValueMap.add("Academic_year", course.getAcademic_year());
	    multiValueMap.add("Semester", Integer.toString(course.getSemester()));
	    multiValueMap.add("Description", course.getDescription());
	    multiValueMap.add("Syllabus", course.getSyllabus());
	    multiValueMap.add("professor", course.getProfessor());
	    */
		mockMvc.perform(
				post("/courses/delete?courseId="+21)
				//.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/courses/list"));	
	}
}
