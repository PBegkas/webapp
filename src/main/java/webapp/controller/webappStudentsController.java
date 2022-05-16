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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import webapp.entity.StudentRegistration;
import webapp.service.StudentRegistrationService;


@Controller
@RequestMapping("/students")
public class webappStudentsController {
	
	
	private int currentCourse;
	
	@Autowired
	private StudentRegistrationService studentService;
	
	public webappStudentsController(StudentRegistrationService theStudentService) {
		studentService = theStudentService;
	}
	
	@RequestMapping("/list")
	public String listStudents(@RequestParam("courseId") int theId, Model theModel) {
		 
		currentCourse = theId;
		System.out.println(currentCourse);
		theModel.addAttribute("course", currentCourse);
		
		// Get the username of the professor
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		// TODO remove this diagnostic
		System.out.println("current user in students:");
		System.out.println(currentPrincipalName);
		
		
		// Get courses based on user
		List<StudentRegistration> thestudents = studentService.findRegistrationsByCourseID(currentCourse);
		
		// add the courses to the model
		theModel.addAttribute("students", thestudents);
		
		// use this template
		return "students/list-students";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		StudentRegistration theStudent = new StudentRegistration();
		
		theModel.addAttribute("student", theStudent);
		theModel.addAttribute("courseId", currentCourse);
		
		return "students/student-form";
	}
	
	@RequestMapping("/save")
	public String saveStudent(@ModelAttribute("student") StudentRegistration theStudent, Model theModel) {
		
		// save the student
		// TODO create the class courseService
		
		theStudent.setCourseID(currentCourse);
		theStudent.setExamGrade(-1);
		theStudent.setProjectGrade(-1);
		theStudent.setOverallGrade(-1);
		studentService.save(theStudent);
		
		// TODO remove this diagnostic
		System.out.println("student to be registered:");
		System.out.println(theStudent);
		
		//theModel.addAttribute("courseId", currentCourse);
		
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/students/list?courseId=" + currentCourse;
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {
		
		// delete the course
		// TODO create the class courseService
		studentService.deleteByID(theId);
		
		// redirect to /courses/list
		return "redirect:/students/list?courseId=" + currentCourse;
		
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {
		
		// get the student from the service
		StudentRegistration theStudent = studentService.findById(theId);
		
		// set student as a model attribute to pre-populate the form
		theModel.addAttribute("student", theStudent);
		
		// send over to our form
		return "students/student-form";			
	}
	
	@RequestMapping("/showFormForGrades")
	public String Grades(@RequestParam("studentId") int theId, Model theModel) {
		
		List<StudentRegistration> theStudents = (List<StudentRegistration>) theModel.getAttribute("students");
		theStudents.size();
		
		// get the employee from the service
		//Course theCourse = courseService.findById(theId);
		
		// set employee as a model attribute to pre-populate the form
		//theModel.addAttribute("course", theCourse);
		
		// send over to our form
		return "students/grade-form";			
	}
}
