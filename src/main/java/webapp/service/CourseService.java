package webapp.service;

import java.util.List;

import webapp.entity.Course;

public interface CourseService {
	
	public List<Course> findCourseByInstructorLogin();
	
	public void save(Course theCourse);
	
	public void deleteByID(int theId);
	
	public void update(Course theCourse);
	

}
