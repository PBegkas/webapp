package webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webapp.dao.StudentRegistrationDAO;
import webapp.entity.StudentRegistration;

@Service
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
	public List<StudentRegistration> findRegistrationsByCourseID(int id) {
		return StudentRegRepository.findByCourseId(id);
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
	
	@Override
	@Transactional
	public StudentRegistration findById(int id) {
		return StudentRegRepository.findById(id);
	}


}
