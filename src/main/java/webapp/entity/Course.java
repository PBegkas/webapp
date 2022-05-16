package webapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="courses")
public class Course {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int ID;
	
	@Column(name="course_id")
	private String courseId;
	
	@Column(name="professor")
	private String professor;
	
	@Column(name="name")
	private String Name;
	
	@Column(name="syllabus")
	private String Syllabus;
	
	@Column(name="academic_year")
	private String Academic_year;
	
	@Column(name="semester")
	private int Semester;  // TODO possibly change it into string
	
	@Column(name="description")
	private String Description;
	
		
	// define constructors
	
	public Course() {
		
	}
	
	public Course(int ID, String CourseId, String Professor, String Name, String Syllabus, String Academic_year, int Semester, String Description) {
		this.ID = ID;
		this.courseId = CourseId;
		this.professor = Professor;
		this.Name = Name;
		this.Syllabus = Syllabus;
		this.Academic_year = Academic_year;
		this.Semester = Semester;
		this.Description = Description;
	}


	public Course(String Name, String Syllabus, String Academic_year, int Semester, String Description) {
		this.Name = Name;
		this.Syllabus = Syllabus;
		this.Academic_year = Academic_year;
		this.Semester = Semester;
		this.Description = Description;
	}

	// define getter/setter
	
	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = id;
	}
	
	public String getCourseId() {
		return courseId;
	}
	
	public void setCourseId(String CourseId) {
		this.courseId = CourseId;
	}
	
	public String getProfessor() {
		return professor;
	}
	
	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getSyllabus() {
		return Syllabus;
	}

	public void setSyllabus(String Syllabus) {
		this.Syllabus = Syllabus;
	}

	public String getAcademic_year() {
		return Academic_year;
	}

	public void setAcademic_year(String Academic_year) {
		this.Academic_year = Academic_year;
	}

	public int getSemester() {
		return Semester;
	}
	
	public void setSemester(int Semester) {
		this.Semester = Semester;
	}
	
	public String getDescription() {
		return Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}
	
	// define tostring

	@Override
	public String toString() {
		return "Course [id=" + ID + ", Course id= " + courseId + ", Professor=" + professor +", Name=" + Name + ", Syllabus=" + Syllabus + ", Academic year=" + Academic_year + ", Semester=" + Semester + ", Description=" + Description +"]";
	}
		
}
