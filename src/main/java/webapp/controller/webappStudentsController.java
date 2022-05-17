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
import webapp.entity.GradeParameters;


@Controller
@RequestMapping("/students")
public class webappStudentsController {
	
	
	//private int currentCourse;
	
	@Autowired
	private StudentRegistrationService studentService;
	
	public webappStudentsController(StudentRegistrationService theStudentService) {
		studentService = theStudentService;
	}
	
	@RequestMapping("/list")
	public String listStudents(@RequestParam("courseId") int theId, Model theModel) {
		 
		//currentCourse = theId;
		System.out.println(theId);
		theModel.addAttribute("courseId", theId);
		
		// Get the username of the professor
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		// TODO remove this diagnostic
		System.out.println("current user in students:");
		System.out.println(currentPrincipalName);
		
		
		// Get students based on course
		List<StudentRegistration> thestudents = studentService.findRegistrationsByCourseID(theId);
		
		// add the students to the model
		theModel.addAttribute("students", thestudents);
		
		// use this template
		return "students/list-students";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(@RequestParam("courseId") int theId, Model theModel) {
		
		// create model attribute to bind form data
		StudentRegistration theStudent = new StudentRegistration();
		
		theModel.addAttribute("student", theStudent);
		theModel.addAttribute("courseId", theId);
		
		return "students/student-form";
	}
	
	@RequestMapping("/save")
	public String saveStudent(@RequestParam("courseId") int theId, @ModelAttribute("student") StudentRegistration theStudent, Model theModel) {
		
		System.out.println("Course to save student with:");
		System.out.println(theId);
		
		// save the student
		// TODO create the class courseService
		StudentRegistration theOldStudent;
		
		// existing student get the old grades
		try {
			theOldStudent = studentService.findById(theStudent.getId());
			theStudent.setExamGrade(theOldStudent.getExamGrade());
			theStudent.setProjectGrade(theOldStudent.getProjectGrade());
			theStudent.setOverallGrade(theOldStudent.getOverallGrade());
			
		// new student initialize grades to 0
		} catch (Exception e) {
			e.printStackTrace();
			theStudent.setExamGrade(-1);
			theStudent.setProjectGrade(-1);
			theStudent.setOverallGrade(-1);
		}
		
		theStudent.setCourseID(theId);
		
		studentService.save(theStudent);
		
		// TODO remove this diagnostic
		System.out.println("student to be saved:");
		System.out.println(theStudent);
		
		//theModel.addAttribute("courseId", currentCourse);
		
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/students/list?courseId=" + theId;
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {
		
		
		int currentCourseId = studentService.findById(theId).getCourseID();
		// delete the course
		// TODO create the class courseService
		studentService.deleteByID(theId);
		
		// redirect to /courses/list
		return "redirect:/students/list?courseId=" + currentCourseId;
		
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {
		
		// get the student from the service
		StudentRegistration theStudent = studentService.findById(theId);
		
		// set student as a model attribute to pre-populate the form
		theModel.addAttribute("student", theStudent);
		theModel.addAttribute("courseId", theStudent.getCourseID());
		
		// send over to our form
		return "students/student-form";			
	}
	
	@RequestMapping("/showFormForGrades")
	public String Grades(@RequestParam("studentId") int theId, Model theModel) {
		
		// get the student from the service
		StudentRegistration theStudent = studentService.findById(theId);
		
		// set student as a model attribute to pre-populate the form
		theModel.addAttribute("student", theStudent);
		theModel.addAttribute("courseId", theStudent.getCourseID());
		
		// send over to our form
		return "students/grade-form";			
	}
	
	@RequestMapping("/back")
	public String GoBack(@RequestParam("courseId") int theId) {
		return "redirect:/students/list?courseId=" + theId;			
	}
	
	@RequestMapping("/saveGrades")
	public String saveGrades(@ModelAttribute("student") StudentRegistration theStudentGrades, Model theModel) {
		
		// TODO remove this diagnostic
		System.out.println("student to register Grades:");
		System.out.println(theStudentGrades);
		
		StudentRegistration theStudent = studentService.findById(theStudentGrades.getId());
		theStudent.setProjectGrade(theStudentGrades.getProjectGrade());
		theStudent.setExamGrade(theStudentGrades.getExamGrade());
		//theStudent.setOverallGrade(theStudentGrades.getOverallGrade());
		
		// save the grades
		studentService.save(theStudent);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/students/list?courseId=" + theStudent.getCourseID();
	}
	
	@RequestMapping("/overall")
	public String overallGrades(@RequestParam("courseId") int theId, Model theModel) {
		
		GradeParameters theGradeParameters = new GradeParameters();
		
		theModel.addAttribute("param", theGradeParameters);
		theModel.addAttribute("courseId", theId);
		
		return "students/overallGrades";
	}
	
	@RequestMapping("/saveParam")
	public String saveParam(@RequestParam("courseId") int theId, @ModelAttribute("param") GradeParameters theGradeParameters, Model theModel) {
		
		List<StudentRegistration> thestudents = studentService.findRegistrationsByCourseID(theId);
		
		for(int i = 0; i < thestudents.size(); i++) {
			StudentRegistration theStudent = thestudents.get(i);
			if(theStudent.getProjectGrade() >= theGradeParameters.getProjectBase() && theStudent.getExamGrade() >= theGradeParameters.getExamBase()) {
				double overallGrade = theStudent.getProjectGrade() * theGradeParameters.getProjectWeight() + theStudent.getExamGrade() * theGradeParameters.getExamWeight();
				if(overallGrade > 10) {
					theStudent.setOverallGrade(10);
				} else {
					theStudent.setOverallGrade(overallGrade);
				}
			} else {
				theStudent.setOverallGrade(0);
			}
			studentService.save(theStudent);
			
		}
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/students/list?courseId=" + theId;
	}
}
