package webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webapp.entity.Course;
import webapp.service.CourseService;

@Controller
@RequestMapping("/courses")
public class webappCoursesController {
	
	private String currentUsername;
	
	@Autowired
	private CourseService courseService;
	
	public webappCoursesController(CourseService theCourseService) {
		courseService = theCourseService;
	}
	
	// mapping for listing the courses
	@RequestMapping("/list")
	public String listCourses(Model theModel) {
		
		// Get the username of the professor
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		currentUsername = authentication.getName();
		
		// TODO remove this diagnostic
		System.out.println("current user:");
		System.out.println(currentUsername);
		
		
		// Get courses based on user
		// TODO add the findByUser method
		List<Course> theCourses = courseService.findCourseByInstructorLogin(currentUsername);
				
		
		// add the courses to the model
		theModel.addAttribute("courses", theCourses);
		
		// use this template
		return "courses/list-courses";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Course theCourse = new Course();
		
		theModel.addAttribute("course", theCourse);
		
		return "courses/course-form";
	}
	
	@RequestMapping("/save")
	public String saveEmployee(@ModelAttribute("course") Course theCourse, Model theModel) {
		
		// add the professor to the course
		theCourse.setProfessor(currentUsername);
		
		// save the employee
		// TODO create the class courseService
		courseService.save(theCourse);
		
		// TODO remove this diagnostic
		System.out.println("course to be saved:");
		System.out.println(theCourse);
		
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/courses/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("courseId") int theId) {
		
		// delete the course
		// TODO create the class courseService
		courseService.deleteByID(theId);
		
		// redirect to /courses/list
		return "redirect:/courses/list";
		
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("courseId") int theId, Model theModel) {
		
		List<Course> theCourses = (List<Course>) theModel.getAttribute("courses");
		theCourses.size();
		
		// get the employee from the service
		//Course theCourse = courseService.findById(theId);
		
		// set employee as a model attribute to pre-populate the form
		//theModel.addAttribute("course", theCourse);
		
		// send over to our form
		return "employees/employee-form";			
	}
	
	
}

