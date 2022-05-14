package webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webapp.entity.Course;

@Repository
public interface CourseDAO extends JpaRepository<Course, Integer>{


}
