package webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import webapp.dao.StudentRegistrationDAO;
import webapp.entity.StudentRegistration;

public class StudentRegistrationServiceImpl implements StudentRegistrationService{
	
	@Autowired
	private StudentRegistrationDAO StudentRegRepository;
	
	public StudentRegistrationServiceImpl() {
		super();
	}

	@Autowired	
	public StudentRegistrationServiceImpl(StudentRegistrationDAO theStudentRegRepository) {
		StudentRegRepository = theStudentRegRepository;
	}
	
	@Override
	@Transactional
	public List<StudentRegistration> findRegistrationByCourseID() {
		return StudentRegRepository.findAll();
	}

	@Override
	@Transactional
	public void save(StudentRegistration theStudentRegistration) {
		StudentRegRepository.save(theStudentRegistration);
		
	}

	@Override
	@Transactional
	public void deleteByID(int theId) {
		StudentRegRepository.deleteById(theId);
		
	}

	@Override
	@Transactional
	public void update(StudentRegistration theStudentRegistration) {
		StudentRegRepository.save(theStudentRegistration);
		
	}


}
