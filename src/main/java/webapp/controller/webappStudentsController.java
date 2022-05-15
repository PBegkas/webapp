package webapp.controller;

import java.util.ArrayList;
import java.util.List;

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


@Controller
@RequestMapping("/students")
public class webappStudentsController {
	
	@RequestMapping("/list")
public String listStudents(Model theModel) {
		
		// Get the username of the professor
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		// TODO remove this diagnostic
		System.out.println("current user in students:");
		System.out.println(currentPrincipalName);
		
		
		// Get courses based on user
		// TODO create the class courseService
		List<Course> theCourses = new ArrayList<Course>();//courseService.findByUser(currentPrincipalName);
		
		
		// add the courses to the model
		theModel.addAttribute("courses", theCourses);
		
		// use this template
		return "students/list-students";
	}
	
}
