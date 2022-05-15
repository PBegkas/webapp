package webapp.service;

import java.util.List;

import webapp.entity.StudentRegistration;

public interface StudentRegistrationService {
	
	public List<StudentRegistration> findRegistrationsByCourseID(int id);
	
	public void save(StudentRegistration theStudent);
	
	public void deleteByID(int theId);
	
	public void update(StudentRegistration theStudent);
	

}
