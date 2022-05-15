package webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webapp.dao.CourseDAO;
import webapp.entity.Course;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseDAO CourseRepository;
	
	public CourseServiceImpl() {
		super();
	}

	@Autowired	
	public CourseServiceImpl(CourseDAO theCourseRepository) {
		CourseRepository = theCourseRepository;
	}
	
	@Override
	@Transactional
	public List<Course> findCourseByInstructorLogin(int id) {
		return CourseRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Course theCourse) {
		CourseRepository.save(theCourse);
		
	}

	@Override
	@Transactional
	public void deleteByID(int theId) {
		CourseRepository.deleteById(theId);
		
	}

	@Override
	@Transactional
	public void update(Course theCourse) {
		CourseRepository.save(theCourse);
		
	}

}
