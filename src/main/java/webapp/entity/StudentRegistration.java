package webapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class StudentRegistration {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int ID;
	
	@Column(name="student_id")
	private String studentId;
	
	@Column(name="course_id")
	private int courseId;
	
	@Column(name="name")
	private String Name;
	
	@Column(name="semester")
	private int Semester;
	
	@Column(name="registration_year")
	private int Registration_Year;
	
	@Column(name="exam_grade")
	private double examGrade;
	
	@Column(name="project_grade")
	private double projectGrade;
	
	@Column(name="overall_grade")
	private double overallGrade;
	
		
	// define constructors
	
	public StudentRegistration() {
		
	}
	
	public StudentRegistration(int ID, String StudentId, int course_id, String Name, int Semester, int Registration_Year, double exam_grade,
							   double project_grade, double overall_grade) {
		this.ID = ID;
		this.studentId = StudentId;
		this.courseId = course_id;
		this.Name = Name;
		this.Semester = Semester;
		this.Registration_Year = Registration_Year;
		this.examGrade = exam_grade;
		this.projectGrade = project_grade;
		this.overallGrade = overall_grade;
	}
	
	// for updating student information
	public StudentRegistration(int ID, String StudentId, int course_id, String Name, int Semester, int Registration_Year) {
		this.ID = ID;
		this.studentId = StudentId;
		this.courseId = course_id;
		this.Name = Name;
		this.Semester = Semester;
		this.Registration_Year = Registration_Year;
	}


	public StudentRegistration(String StudentId, int course_id, String Name, int Semester, int Registration_Year, double exam_grade, double project_grade) {
		this.studentId = StudentId;
		this.courseId = course_id;
		this.Name = Name;
		this.Semester = Semester;
		this.Registration_Year = Registration_Year;
		this.examGrade = exam_grade;
		this.projectGrade = project_grade;

	}
	
	// for enrolling the student
	public StudentRegistration(String StudentId, String Name, int Semester, int Registration_Year) {
		this.studentId = StudentId;
		this.Name = Name;
		this.Semester = Semester;
		this.Registration_Year = Registration_Year;

	}
	// define getter/setter
	
	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = id;
	}
	
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String StudentId) {
		this.studentId = StudentId;
	}
	
	public int getCourseID() {
		return courseId;
	}

	public void setCourseID(int course_id) {
		this.courseId = course_id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public int getSemester() {
		return Semester;
	}
	
	public void setSemester(int Semester) {
		this.Semester = Semester;
	}
	
	public int getRegistration_Year() {
		return Registration_Year;
	}

	public void setRegistration_Year(int Registration_Year) {
		this.Registration_Year = Registration_Year;
	}
	
	public double getExamGrade() {
		return examGrade;
	}

	public void setExamGrade(double exam_grade) {
		this.examGrade = exam_grade;
	}
	
	public double getProjectGrade() {
		return projectGrade;
	}

	public void setProjectGrade(double project_grade) {
		this.projectGrade = project_grade;
	}
	
	public double getOverallGrade() {
		return overallGrade;
	}

	public void setOverallGrade(double overall_grade) {
		this.overallGrade = overall_grade;
	}
	
	// define tostring

	@Override
	public String toString() {
		return "Student [id=" + ID + ", student id=" + studentId + ", course id=" + courseId +", Name=" + Name + 
				", Semester=" + Semester + ", Registration Year=" + Registration_Year + ", exam grade="+ examGrade + 
				", project grade="+ projectGrade +", overall grade="+ overallGrade + "]";
	}
		
}
